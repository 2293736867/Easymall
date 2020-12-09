package pers.wzr.handler

import cn.hutool.captcha.CaptchaUtil
import org.springframework.core.io.ClassPathResource
import org.springframework.core.io.Resource
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.BodyInserters
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import pers.wzr.util.VerificationCodeUtils
import reactor.core.publisher.Mono

@Component
class VerificationHandler {
    fun code(request: ServerRequest): Mono<ServerResponse>
    {
        return ServerResponse.ok().body(Mono.just(VerificationCodeUtils.getBase64()),String::class.java).switchIfEmpty(Mono.empty())
    }
}