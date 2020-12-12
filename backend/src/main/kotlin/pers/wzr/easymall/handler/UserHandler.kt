package pers.wzr.easymall.handler

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Example
import org.springframework.data.domain.ExampleMatcher
import org.springframework.data.domain.ExampleMatcher.GenericPropertyMatchers.exact
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import pers.wzr.easymall.cache.UserQueryCache
import pers.wzr.easymall.dao.UserRepository
import pers.wzr.easymall.entity.entity.User
import pers.wzr.easymall.entity.builder.UserBuilder
import pers.wzr.easymall.entity.property.UserProperty
import pers.wzr.easymall.entity.validation.UserSignInAndSignUp
import pers.wzr.easymall.response.Response
import pers.wzr.easymall.response.ResponseCode
import pers.wzr.easymall.util.JwtUtils
import pers.wzr.easymall.validator.CustomValidator
import pers.wzr.easymall.util.VerificationCodeUtils
import reactor.core.publisher.Mono

@Component
class UserHandler {
    @Autowired
    lateinit var repository: UserRepository
    @Autowired
    lateinit var validator: CustomValidator

    private var cache = UserQueryCache()

    fun checkUsernameExists(request: ServerRequest):Mono<ServerResponse>
    {
        val username = request.pathVariable("username")
        val user = UserBuilder().username(username).build()
        val matcher = ExampleMatcher.matching().withMatcher(UserProperty.username(), exact())
                .withIgnorePaths(*UserProperty.other())
        return repository.findOne(Example.of(user,matcher)).flatMap {
            Response.build(ResponseCode.USER_USERNAME_EXISTS)
        }.switchIfEmpty(
            Response.build(ResponseCode.USER_USERNAME_NOT_EXISTS)
        )
    }

    fun signIn(request: ServerRequest): Mono<ServerResponse>
    {
        return request.bodyToMono(UserSignInAndSignUp::class.java).flatMap {
            if(validator.hasErrors(it))
                return@flatMap validator.errors()
            val username = it.username
            val password = it.password
            val code = it.code
            if(VerificationCodeUtils.verify(code))
            {
                val user = UserBuilder().username(username).password(password).build()
                val matcher = ExampleMatcher.matching()
                    .withMatcher(UserProperty.username(), exact())
                    .withMatcher(UserProperty.password(), exact())
                    .withIgnorePaths(*UserProperty.other())
                return@flatMap repository.findOne(Example.of(user,matcher)).flatMap {
                    Response.build(ResponseCode.USER_SIGN_IN_SUCCESS)
                }.switchIfEmpty(
                    Response.build(ResponseCode.USER_SIGN_IN_FAILED_USERNAME_OR_PASSWORD_ERROR)
                )
            }
            return@flatMap Response.build(ResponseCode.USER_SIGN_IN_FAILED_VERIFICATION_CODE_ERROR)
        }
    }

    fun signUp(request:ServerRequest): Mono<ServerResponse>
    {
        return request.bodyToMono(UserSignInAndSignUp::class.java).flatMap {
            if(validator.hasErrors(it))
                return@flatMap validator.errors()
            if(VerificationCodeUtils.verify(it.code))
            {
                val username = it.username
                val password = it.password
                repository.save(UserBuilder().username(username).password(password).build())
                return@flatMap request.session().flatMap {t->
                    t.attributes["token"] = JwtUtils.generateJWT(username,password)
                    Response.build(ResponseCode.USER_SIGN_UP_SUCCESS)
                }
            }
            return@flatMap Response.build(ResponseCode.USER_SIGN_UP_FAILED_VERIFICATION_CODE_ERROR)
        }
    }

    fun update(request: ServerRequest):Mono<ServerResponse>
    {
        return request.bodyToMono(User::class.java).flatMap {
            repository.save(it).then(Response.build(ResponseCode.USER_UPDATE_SUCCESS))
        }.switchIfEmpty(
            Response.build(ResponseCode.USER_UPDATE_FAILED_NOT_EXISTS)
        )
    }

    fun delete(request: ServerRequest):Mono<ServerResponse>
    {
        return repository.findById(request.pathVariable("id"))
        .flatMap {
            repository.delete(it).then(Response.build(ResponseCode.USER_DELETE_SUCCESS))
        }.switchIfEmpty(
            Response.build(ResponseCode.USER_DELETE_FAILED_NOT_FOUND)
        )
    }

    fun getAll(request: ServerRequest):Mono<ServerResponse>
    {
        cache.fluxUser = repository.findAll()
        return Response.build(ResponseCode.USER_GET_ALL_SUCCESS)
    }

    fun getById(request: ServerRequest):Mono<ServerResponse>
    {
        val u = repository.findById(request.pathVariable("id"))
        cache.monoUser = u
        return u.flatMap {
            Response.build(ResponseCode.USER_GET_ONE_SUCCESS)
        }.switchIfEmpty(
            Response.build(ResponseCode.USER_GET_ONE_FAILED_NOT_EXISTS)
        )
    }

    fun data(request: ServerRequest):Mono<ServerResponse>
    {
        return when(request.pathVariable("code")){
            ResponseCode.USER_GET_ONE_SUCCESS -> Response.build(cache.monoUser, User::class.java)
            ResponseCode.USER_GET_ALL_SUCCESS -> Response.build(cache.fluxUser, User::class.java)
            else -> Response.build(ResponseCode.ERROR_GET_GATE_CODE)
        }
    }
}