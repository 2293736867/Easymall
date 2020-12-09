package pers.wzr.userservice.handler

import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import reactor.core.publisher.Mono

@Component
class Handler {
    fun test(request: ServerRequest):Mono<ServerResponse>
    = ServerResponse.ok().body(Mono.just("user success"),String::class.java)

}