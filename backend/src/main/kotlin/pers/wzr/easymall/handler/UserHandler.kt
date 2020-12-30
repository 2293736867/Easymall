package pers.wzr.easymall.handler

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.data.domain.Example
import org.springframework.data.domain.ExampleMatcher
import org.springframework.data.domain.ExampleMatcher.GenericPropertyMatchers.exact
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import pers.wzr.easymall.cache.UserCache
import pers.wzr.easymall.dao.UserRepository
import pers.wzr.easymall.entity.builder.UserBuilder
import pers.wzr.easymall.entity.property.UserProperty
import pers.wzr.easymall.entity.validation.UserSignInAndSignUp
import pers.wzr.easymall.entity.validation.UserUpdate
import pers.wzr.easymall.response.CommonResponse
import pers.wzr.easymall.response.ResponseCode
import pers.wzr.easymall.response.UserResponse
import pers.wzr.easymall.util.JWT
import pers.wzr.easymall.validator.CustomValidator
import pers.wzr.easymall.util.VerificationCodeUtils
import pers.wzr.easymall.validator.ValidationGroup
import reactor.core.publisher.Mono

@Component
class UserHandler {
    @Autowired
    lateinit var repository: UserRepository
    @Autowired
    lateinit var validator: CustomValidator

    @Value("jwt.key")
    lateinit var key:String

    private var cache = UserCache()

    fun checkUsernameExists(request: ServerRequest):Mono<ServerResponse>
    {
        val username = request.pathVariable("username")
        val user = UserBuilder().username(username).build()
        val matcher = ExampleMatcher.matching().withMatcher(UserProperty.username(), exact())
                .withIgnorePaths(*UserProperty.other())
        return repository.findOne(Example.of(user,matcher)).flatMap {
            CommonResponse.code(ResponseCode.USER_USERNAME_EXISTS)
        }.switchIfEmpty(
            CommonResponse.code(ResponseCode.USER_USERNAME_NOT_EXISTS)
        )
    }

    fun signIn(request: ServerRequest): Mono<ServerResponse>
    {
        return request.bodyToMono(UserSignInAndSignUp::class.java).flatMap {
            if(validator.hasErrors(it,ValidationGroup.UserSignIn::class.java))
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
                    cache.username = username
                    CommonResponse.code(ResponseCode.USER_SIGN_IN_SUCCESS)
                }.switchIfEmpty(
                    CommonResponse.code(ResponseCode.USER_SIGN_IN_FAILED_USERNAME_OR_PASSWORD_ERROR)
                )
            }
            return@flatMap CommonResponse.code(ResponseCode.USER_SIGN_IN_FAILED_VERIFICATION_CODE_ERROR)
        }
    }

    fun signUp(request:ServerRequest): Mono<ServerResponse>
    {
        return request.bodyToMono(UserSignInAndSignUp::class.java).flatMap {
            if(validator.hasErrors(it,ValidationGroup.UserSignUp::class.java))
                return@flatMap validator.errors()
            if(VerificationCodeUtils.verify(it.code))
            {
                val username = it.username
                val password = it.password
                return@flatMap repository.save(UserBuilder().username(username).password(password).email(it.email).build()).flatMap {
                    CommonResponse.code(ResponseCode.USER_SIGN_UP_SUCCESS)
                }
            }
            return@flatMap CommonResponse.code(ResponseCode.USER_SIGN_UP_FAILED_VERIFICATION_CODE_ERROR)
        }
    }

    fun update(request: ServerRequest):Mono<ServerResponse>
    {
        return request.bodyToMono(UserUpdate::class.java).flatMap {
            if(validator.hasErrors(it))
                return@flatMap validator.errors()
            val username = it.username
            val user = UserBuilder().username(username).build()
            val matcher = ExampleMatcher.matching().withMatcher(UserProperty.username(), exact())
                .withIgnorePaths(*UserProperty.other())
            repository.findOne(Example.of(user,matcher)).flatMap { n ->
                user.id = n.id
                user.email = it.email
                user.nickname = it.nickname
                repository.save(user).then(CommonResponse.code(ResponseCode.USER_UPDATE_SUCCESS))
            }.switchIfEmpty(
                CommonResponse.code(ResponseCode.USER_UPDATE_FAILED_NOT_FOUND)
            )
        }
    }

    fun delete(request: ServerRequest):Mono<ServerResponse>
    {
        return repository.findById(request.pathVariable("id"))
        .flatMap {
            repository.delete(it).then(CommonResponse.code(ResponseCode.USER_DELETE_SUCCESS))
        }.switchIfEmpty(
            CommonResponse.code(ResponseCode.USER_DELETE_FAILED_NOT_FOUND)
        )
    }

    fun getAll(request: ServerRequest):Mono<ServerResponse>
    {
        cache.fluxUser = repository.findAll()
        return CommonResponse.code(ResponseCode.USER_GET_ALL_SUCCESS)
    }

    fun getById(request: ServerRequest):Mono<ServerResponse>
    {
        val u = repository.findById(request.pathVariable("id"))
        cache.monoUser = u
        return u.flatMap {
            CommonResponse.code(ResponseCode.USER_GET_ONE_SUCCESS)
        }.switchIfEmpty(
            CommonResponse.code(ResponseCode.USER_GET_ONE_FAILED_NOT_FOUND)
        )
    }

    fun data(request: ServerRequest):Mono<ServerResponse>
    {
        return when(request.pathVariable("code")){
            ResponseCode.USER_GET_ONE_SUCCESS -> UserResponse.mono(cache.monoUser)
            ResponseCode.USER_GET_ALL_SUCCESS -> UserResponse.flux(cache.fluxUser)
            ResponseCode.USER_SIGN_IN_SUCCESS -> UserResponse.token(JWT.generate(cache.username,key))
            else -> CommonResponse.code(ResponseCode.ERROR_GET_GATE_CODE)
        }
    }
}