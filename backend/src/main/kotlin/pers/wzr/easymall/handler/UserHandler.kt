package pers.wzr.easymall.handler

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Example
import org.springframework.data.domain.ExampleMatcher
import org.springframework.data.domain.ExampleMatcher.GenericPropertyMatchers.exact
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import pers.wzr.easymall.dao.UserRepository
import pers.wzr.easymall.entity.entity.User
import pers.wzr.easymall.entity.property.UserProperty
import pers.wzr.easymall.entity.response.UserSignInResponse
import pers.wzr.easymall.response.JSONResponse
import pers.wzr.easymall.response.ResponseCode
import pers.wzr.easymall.util.JWTUtils
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

    fun checkUsernameExists(request: ServerRequest):Mono<ServerResponse>
    {
        val user = User(username = request.pathVariable("username"))
        val matcher = ExampleMatcher.matching().withMatcher(UserProperty.username(), exact())
                .withIgnorePaths(*UserProperty.other())
        return repository.findOne(Example.of(user,matcher)).flatMap {
            JSONResponse.code(ResponseCode.USER_USERNAME_EXISTS)
        }.switchIfEmpty(
            JSONResponse.code(ResponseCode.USER_USERNAME_NOT_EXISTS)
        )
    }

    fun signIn(request: ServerRequest): Mono<ServerResponse>
    {
        return request.bodyToMono(User::class.java).flatMap {
            if(validator.hasErrors(it,ValidationGroup.UserSignIn::class.java))
                return@flatMap validator.errors()
            if(VerificationCodeUtils.verify(it.code))
            {
                val user = User(username = it.username,password = it.password)
                val matcher = ExampleMatcher.matching()
                    .withMatcher(UserProperty.username(), exact())
                    .withMatcher(UserProperty.password(), exact())
                    .withIgnorePaths(*UserProperty.other())
                return@flatMap repository.findOne(Example.of(user,matcher)).flatMap { u->
                    JSONResponse.codeAndData(ResponseCode.USER_SIGN_IN_SUCCESS,UserSignInResponse(email = u.email, nickname = u.nickname,token = JWTUtils.generate(u.id)))
                }.switchIfEmpty(
                    JSONResponse.code(ResponseCode.USER_SIGN_IN_FAILED_USERNAME_OR_PASSWORD_ERROR)
                )
            }
            return@flatMap JSONResponse.code(ResponseCode.USER_SIGN_IN_FAILED_VERIFICATION_CODE_ERROR)
        }
    }

    fun add(request: ServerRequest) = request.bodyToMono(User::class.java).flatMap{
        return@flatMap repository.save(it).flatMap{ u ->
            JSONResponse.codeAndData(ResponseCode.USER_ADD_SUCCESS,u.id)
        }.switchIfEmpty(
            JSONResponse.code(ResponseCode.USER_ADD_FAILED)
        )
    }

    fun signUp(request:ServerRequest): Mono<ServerResponse>
    {
        return request.bodyToMono(User::class.java).flatMap {
            if(validator.hasErrors(it,ValidationGroup.UserSignUp::class.java))
                return@flatMap validator.errors()
            if(VerificationCodeUtils.verify(it.code))
            {
                return@flatMap repository.save(it).flatMap {
                    JSONResponse.code(ResponseCode.USER_SIGN_UP_SUCCESS)
                }
            }
            return@flatMap JSONResponse.code(ResponseCode.USER_SIGN_UP_FAILED_VERIFICATION_CODE_ERROR)
        }
    }

    fun update(request: ServerRequest):Mono<ServerResponse>
    {
        return request.bodyToMono(User::class.java).flatMap {
            if(validator.hasErrors(it,ValidationGroup.UserUpdate::class.java))
                return@flatMap validator.errors()
            return@flatMap repository.findById(it.id).flatMap { old ->
                it.password = old.password
                repository.save(it).then(JSONResponse.code(ResponseCode.USER_UPDATE_SUCCESS))
            }.switchIfEmpty(
                JSONResponse.code(ResponseCode.USER_UPDATE_FAILED_NOT_FOUND)
            )
        }
    }

    fun delete(request: ServerRequest):Mono<ServerResponse>
    {
        val id = request.pathVariable("id")
        return repository.findById(id).flatMap {
            repository.deleteById(id).then(JSONResponse.code(ResponseCode.USER_DELETE_SUCCESS))
        }.switchIfEmpty(
            JSONResponse.code(ResponseCode.USER_DELETE_FAILED_NOT_FOUND)
        )
    }

    fun getAll(request: ServerRequest):Mono<ServerResponse>
    {
        return repository.findAll().collectList().flatMap {
            JSONResponse.codeAndData(ResponseCode.USER_GET_ALL_SUCCESS,it)
        }.switchIfEmpty(
            JSONResponse.code(ResponseCode.USER_GET_ALL_FAILED_EMPTY)
        )
    }
}