package pers.wzr.commons.response

import org.springframework.web.reactive.function.BodyInserters
import org.springframework.web.reactive.function.server.ServerResponse
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

class Response{

    companion object{
        fun build(code:String):Mono<ServerResponse> = ServerResponse.ok().body(BodyInserters.fromValue(code))

        fun <T> build(t:Mono<T>,c:Class<*>): Mono<ServerResponse>
        {
            return ServerResponse.ok().body(t,c)
        }

        fun <T> build(t: Flux<T>, c:Class<*>): Mono<ServerResponse>
        {
            return ServerResponse.ok().body(t,c)
        }
    }

}
