package pers.wzr.verificationcode.handler

import cn.hutool.captcha.ShearCaptcha
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import pers.wzr.verificationcode.utils.Utils
import reactor.core.publisher.Mono

@Component
class Handler {
    companion object{
        private var code = ShearCaptcha(150,25)

        fun getBase64():String {
            code = ShearCaptcha(150,25,4,0)
            return code.imageBase64
        }

        fun verify(c:String) = code.verify(c)
    }

    fun get(request: ServerRequest): Mono<ServerResponse>{
        return ServerResponse.ok().body(Mono.just(getBase64()),String::class.java).switchIfEmpty(Mono.empty())
    }
}