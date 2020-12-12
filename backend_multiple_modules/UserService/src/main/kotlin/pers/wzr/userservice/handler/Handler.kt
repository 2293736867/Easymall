package pers.wzr.userservice.handler

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Example
import org.springframework.data.domain.ExampleMatcher
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import pers.wzr.commons.response.Response
import pers.wzr.commons.response.ResponseCode
import pers.wzr.commons.verification.code.VerificationCodeUtils
import pers.wzr.userservice.cache.QueryCache
import pers.wzr.userservice.dao.Repository
import pers.wzr.userservice.entity.User
import pers.wzr.userservice.entity.UserBuilder
import reactor.core.publisher.Mono

@Component
class Handler {
    @Autowired
    lateinit var repository:Repository

    private var cache = QueryCache()

    fun checkUsernameExists(request: ServerRequest):Mono<ServerResponse>
    {
        val user = UserBuilder().username(request.pathVariable("username")).build()
        val matcher = ExampleMatcher.matching().withMatcher("username", ExampleMatcher.GenericPropertyMatchers.exact())
            .withIgnorePaths("id","password","email","nickname")
        return repository.findOne(Example.of(user,matcher)).flatMap { b->
            Response.build(ResponseCode.USER_USERNAME_EXISTS)
        }.switchIfEmpty(
            Response.build(ResponseCode.USER_USERNAME_NOT_EXISTS)
        )
    }

    fun signIn(request: ServerRequest): Mono<ServerResponse>
    {
        val username = request.queryParam("username").get()
        val password = request.queryParam("password").get()
        val code = request.queryParam("code").get()
        if(VerificationCodeUtils.verify(code))
        {
            val user = UserBuilder().username(username).password(password).build()
            val matcher = ExampleMatcher.matching()
                .withMatcher("username", ExampleMatcher.GenericPropertyMatchers.exact())
                .withMatcher("password", ExampleMatcher.GenericPropertyMatchers.exact())
                .withIgnorePaths("id","nickname","email")
            return repository.findOne(Example.of(user,matcher)).flatMap {
                Response.build(ResponseCode.USER_SIGN_IN_SUCCESS)
            }
        }
        return Response.build(ResponseCode.USER_SIGN_IN_FAILED_VERIFICATION_CODE_ERROR)
    }

    fun signUp(request:ServerRequest): Mono<ServerResponse>
    {
        val code = request.queryParam("code").get()
        if(VerificationCodeUtils.verify(code))
        {
            return request.bodyToMono(User::class.java).flatMap { u->
                repository.save(u).then(request.session().flatMap { t->
                    Response.build(ResponseCode.USER_SIGN_UP_SUCCESS)
//                    Response().code(ResponseCode.USER_SIGN_UP_SUCCESS).data(JwtUtils.generateJWT(u.id,u.password)).build()
                })
            }
        }
        return Response.build(ResponseCode.USER_SIGN_UP_FAILED_VERIFICATION_CODE_ERROR)
    }

    fun delete(request: ServerRequest):Mono<ServerResponse>
    {
        return repository.findById(request.pathVariable("id"))
            .flatMap { u->
                repository.delete(u)
                    .then(Response.build(ResponseCode.USER_DELETE_SUCCESS))
            }.switchIfEmpty(Response.build(ResponseCode.USER_DELETE_FAILED_NOT_FOUND))
    }

    fun getAll(request: ServerRequest):Mono<ServerResponse>
    {
        cache.fluxUser = repository.findAll()
        return Response.build(ResponseCode.USER_GET_ALL_SUCCESS)
    }

    fun getById(request: ServerRequest):Mono<ServerResponse>
    {
        val u = repository.findById(request.pathVariable("id"))
        return u.flatMap{
            cache.monoUser = u
            Response.build(ResponseCode.USER_GET_ONE_SUCCESS)
        }
        .switchIfEmpty(Response.build(ResponseCode.USER_GET_ONE_FAILED_NOT_EXISTS))
    }

    fun data(request: ServerRequest):Mono<ServerResponse>
    {
        val code = request.pathVariable("code")
        if(code == ResponseCode.USER_GET_ALL_SUCCESS)
            return Response.build(cache.fluxUser,User::class.java)
        if(code == ResponseCode.USER_GET_ONE_SUCCESS)
            return Response.build(cache.monoUser,User::class.java)
        return Response.build(ResponseCode.ERROR_CODE)
    }
}