package pers.wzr.easymall.filter

import org.springframework.web.reactive.function.server.HandlerFilterFunction
import org.springframework.web.reactive.function.server.HandlerFunction
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import pers.wzr.easymall.response.CommonResponse
import pers.wzr.easymall.response.ResponseCode
import pers.wzr.easymall.util.Utils
import reactor.core.publisher.Mono

class UserHandlerFilter:HandlerFilterFunction<ServerResponse,ServerResponse> {
    override fun filter(request: ServerRequest, next: HandlerFunction<ServerResponse>): Mono<ServerResponse> {
        val path = request.path()
        if(path == "/user/update")
        {
            val s = Utils.getUserTokenFromServerRequest(request)
            if(s.isEmpty())
            {
                return CommonResponse.code(ResponseCode.USER_NOT_SIGN_IN)
            }
        }
        return next.handle(request)
    }
}