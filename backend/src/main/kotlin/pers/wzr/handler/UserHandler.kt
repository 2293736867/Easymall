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
import pers.wzr.response.ResponseCode
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
            ServerResponse.ok().body(Mono.just(ResponseCode.USERNAME_EXISTS),String::class.java)
        }.switchIfEmpty(
            ServerResponse.ok().body(Mono.just(ResponseCode.USERNAME_NOT_EXISTS),String::class.java)
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
            return repository.findOne(Example.of(user,matcher)).flatMap { t->
                ServerResponse.ok().body(Mono.just(ResponseCode.SIGN_IN_SUCCESS),String::class.java)
            }.switchIfEmpty(
                ServerResponse.ok().body(Mono.just(ResponseCode.SIGN_IN_FAILED_USERNAME_OR_PASSWORD_ERROR),String::class.java)
            )
        }
        return ServerResponse.ok().body(Mono.just(ResponseCode.SIGN_IN_FAILED_VERIFICATION_CODE_ERROR),String::class.java)
    }

    fun signUp(request:ServerRequest): Mono<ServerResponse>
    {
        val code = request.queryParam("code").get()
        if(VerificationCodeUtils.verify(code))
        {
            return request.bodyToMono(User::class.java).flatMap { u->
//                println(u)
//                println(u.id)
//                println(u.nickname)
                repository.save(u).then(ServerResponse.ok().body(Mono.just(ResponseCode.SIGN_UP_SUCCESS),String::class.java))
//                ServerResponse.ok().body(repository.save(u),User::class.java)
//                    ResponseCode.SIGN_UP_SUCCESS
//                } ,String::class.java)
            }
        }
        return ServerResponse.ok().body(Mono.just(ResponseCode.SIGN_UP_FAILED_VERIFICATION_CODE_ERROR),String::class.java)
    }

    fun delete(request: ServerRequest):Mono<ServerResponse>
    {
        return repository.findById(request.pathVariable("id"))
        .flatMap { u->
            repository.delete(u)
            .then(ServerResponse.ok().build())
        }.switchIfEmpty(ServerResponse.notFound().build())
    }

    fun selectAll(request: ServerRequest):Mono<ServerResponse>
    {
        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).body(repository.findAll(),User::class.java)
    }

    fun selectById(request: ServerRequest):Mono<ServerResponse>
    {
        return repository.findById(request.pathVariable("id")).flatMap { u->
            ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).body(Mono.just(u),User::class.java)
        }.switchIfEmpty(ServerResponse.notFound().build())
    }
}