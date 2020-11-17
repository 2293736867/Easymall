package pers.wzr.handler

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Example
import org.springframework.data.domain.ExampleMatcher
import org.springframework.data.domain.ExampleMatcher.GenericPropertyMatchers.exact
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.BodyInserter
import org.springframework.web.reactive.function.BodyInserters
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import pers.wzr.dao.UserRepository
import pers.wzr.entity.User
import pers.wzr.entity.UserBuilder
import pers.wzr.util.VerificationCodeUtils
import reactor.core.publisher.Mono

@Component
class UserHandler {
    @Autowired
    lateinit var repository: UserRepository

    fun login(request: ServerRequest): Mono<ServerResponse>
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
                    .withIgnorePaths("id")
            return repository.findOne(Example.of(user, matcher)).flatMap { u ->
                ServerResponse.ok().body(Mono.just(u), User::class.java)
            }
        }
        return ServerResponse.ok().body(Mono.just("10001"),String::class.java)
    }

    fun save(request:ServerRequest): Mono<ServerResponse>
    {
        return request.bodyToMono(User::class.java).flatMap { u->
            ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).body(repository.save(u),User::class.java)
        }
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