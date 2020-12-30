package pers.wzr.easymall.response

import org.springframework.core.io.ClassPathResource
import org.springframework.web.reactive.function.BodyInserters
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.body
import pers.wzr.easymall.entity.entity.User
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

class UserResponse{

    companion object{
        fun mono(t:Mono<User>): Mono<ServerResponse>
        {
            return ServerResponse.ok().body(t)
        }

        fun flux(t: Flux<User>): Mono<ServerResponse>
        {
            return ServerResponse.ok().body(t)
        }

        fun verificationCode(base64VerificationCode:String):Mono<ServerResponse>
        {
            return ServerResponse.ok().body(BodyInserters.fromValue(base64VerificationCode)).switchIfEmpty(Mono.empty())
        }

        fun token(token:String):Mono<ServerResponse>{
            return ServerResponse.ok().body(BodyInserters.fromValue(token))
        }
    }
}
