package pers.wzr.easymall.handler

import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import pers.wzr.easymall.response.UserResponse
import pers.wzr.easymall.util.VerificationCodeUtils
import reactor.core.publisher.Mono

@Component
class VerificationHandler {
    fun code(request: ServerRequest): Mono<ServerResponse>
    {
        return UserResponse.verificationCode(VerificationCodeUtils.getBase64())
    }
}