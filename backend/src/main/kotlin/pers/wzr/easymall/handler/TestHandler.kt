package pers.wzr.easymall.handler

import org.springframework.http.HttpStatus
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.body
import reactor.core.publisher.Mono

@Component
class TestHandler {
    fun test(request: ServerRequest): Mono<ServerResponse> {
        return request.session().flatMap { t->
            ServerResponse.ok().body(Mono.just("token is "+t.attributes["token"] as String))
        }
    }
}