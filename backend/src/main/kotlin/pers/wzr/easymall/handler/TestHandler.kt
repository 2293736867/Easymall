package pers.wzr.easymall.handler

import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import pers.wzr.easymall.entity.entity.User
import pers.wzr.easymall.response.JSONResponse
import reactor.core.publisher.Mono

@Component
class TestHandler {
    fun test(request: ServerRequest): Mono<ServerResponse> {
        return JSONResponse.codeAndData("cc",User())
    }
}
