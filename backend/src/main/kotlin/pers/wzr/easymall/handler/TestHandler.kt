package pers.wzr.easymall.handler

import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.BodyInserters
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import reactor.core.publisher.Mono

@Component
class TestHandler {
    fun test(request: ServerRequest): Mono<ServerResponse> {
        val id = request.pathVariable("id")
        println(id)
        if(id.isEmpty())
        {
            println("id is empty")
        }
        else
        {
            println("id is $id")
        }
        return ServerResponse.ok().body(BodyInserters.fromValue("ccc"))
//        return request.session().flatMap { t->
//            ServerResponse.ok().body(Mono.just("token is "+t.attributes["token"] as String))
//        }
    }
}