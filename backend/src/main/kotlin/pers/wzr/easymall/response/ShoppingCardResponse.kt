package pers.wzr.easymall.response

import org.springframework.core.io.ClassPathResource
import org.springframework.web.reactive.function.BodyInserters
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.body
import pers.wzr.easymall.entity.entity.ShoppingCard
import pers.wzr.easymall.entity.entity.User
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

class ShoppingCardResponse{

    companion object{
        fun mono(t:Mono<ShoppingCard>): Mono<ServerResponse>
        {
            return ServerResponse.ok().body(t)
        }

        fun flux(t: Flux<ShoppingCard>): Mono<ServerResponse>
        {
            return ServerResponse.ok().body(t)
        }
    }
}
