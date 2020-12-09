package pers.wzr.handler

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Example
import org.springframework.data.domain.ExampleMatcher
import org.springframework.data.domain.ExampleMatcher.GenericPropertyMatchers.exact
import org.springframework.http.MediaType
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import pers.wzr.dao.UserRepository
import pers.wzr.entity.User
import pers.wzr.entity.UserBuilder
import pers.wzr.response.Response
import pers.wzr.response.ResponseCode
import pers.wzr.util.JwtUtils
import pers.wzr.util.VerificationCodeUtils
import reactor.core.publisher.Mono

@Component
class UserHandler {
    @Autowired
    lateinit var repository: UserRepository

    fun checkUsernameExists(request: ServerRequest):Mono<ServerResponse>
    {
        val username = request.pathVariable("username")
        val user = UserBuilder().username(username).build()
        val matcher = ExampleMatcher.matching().withMatcher("username", exact())
                .withIgnorePaths("id","password","email","nickname")
        return repository.findOne(Example.of(user,matcher)).flatMap { b->
            Response().code(ResponseCode.USER_USERNAME_EXISTS).build()
        }.switchIfEmpty(
            Response().code(ResponseCode.USER_USERNAME_NOT_EXISTS).build()
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
                    .withMatcher("username", exact())
                    .withMatcher("password", exact())
                    .withIgnorePaths("id","nickname","email")
            return repository.findOne(Example.of(user,matcher)).flatMap {
                    Response().code(ResponseCode.USER_SIGN_IN_SUCCESS).build()
            }
        }
        return Response().code(ResponseCode.USER_SIGN_IN_FAILED_VERIFICATION_CODE_ERROR).build()
    }

    fun signUp(request:ServerRequest): Mono<ServerResponse>
    {
        val code = request.queryParam("code").get()
        if(VerificationCodeUtils.verify(code))
        {
            return request.bodyToMono(User::class.java).flatMap { u->
                repository.save(u).then(request.session().flatMap { t->
                    t.attributes["token"] = JwtUtils.generateJWT(u.id,u.password)
                    Response().code(ResponseCode.USER_SIGN_UP_SUCCESS).build()
                })
            }
        }
        return Response().code(ResponseCode.USER_SIGN_UP_FAILED_VERIFICATION_CODE_ERROR).build()
    }

    fun delete(request: ServerRequest):Mono<ServerResponse>
    {
        return repository.findById(request.pathVariable("id"))
        .flatMap { u->
            repository.delete(u)
            .then(Response().code(ResponseCode.USER_DELETE_SUCCESS).build())
        }.switchIfEmpty(Response().code(ResponseCode.USER_DELETE_FAILED_NOT_FOUND).build())
    }

    fun getAll(request: ServerRequest):Mono<ServerResponse>
    {
        return Response().code(ResponseCode.USER_GET_ALL_SUCCESS).data(repository.findAll()).build()
    }

    fun getById(request: ServerRequest):Mono<ServerResponse>
    {
        return repository.findById(request.pathVariable("id")).flatMap { u->
            Response().code(ResponseCode.USER_GET_ONE_SUCCESS).data(u).build()
        }.switchIfEmpty(Response().code(ResponseCode.USER_GET_ONE_FAILED_NOT_EXISTS).build())
    }
}