package pers.wzr.easymall.filter

import org.springframework.web.reactive.function.server.HandlerFilterFunction
import org.springframework.web.reactive.function.server.HandlerFunction
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import pers.wzr.easymall.entity.util.UserUtils
import pers.wzr.easymall.response.JSONResponse
import pers.wzr.easymall.response.ResponseCode
import reactor.core.publisher.Mono

class UserHandlerFilter:HandlerFilterFunction<ServerResponse,ServerResponse> {
    override fun filter(request: ServerRequest, next: HandlerFunction<ServerResponse>): Mono<ServerResponse> {
        return next.handle(request)
    }
}