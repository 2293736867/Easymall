package pers.wzr.easymall.response

import org.springframework.core.io.ClassPathResource
import org.springframework.web.reactive.function.BodyInserters
import org.springframework.web.reactive.function.server.ServerResponse
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

class CommonResponse{
    companion object{
        fun code(code:String):Mono<ServerResponse> {
            return ServerResponse.ok().body(BodyInserters.fromValue(code))
        }

        fun <T> mono(t:Mono<T>, c:Class<*>): Mono<ServerResponse>
        {
            return ServerResponse.ok().body(t,c)
        }

        fun <T> flux(t: Flux<T>, c:Class<*>): Mono<ServerResponse>
        {
            return ServerResponse.ok().body(t,c)
        }
    }
}
