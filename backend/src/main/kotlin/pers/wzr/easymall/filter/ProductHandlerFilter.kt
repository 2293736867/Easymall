package pers.wzr.easymall.filter

import org.springframework.web.reactive.function.server.HandlerFilterFunction
import org.springframework.web.reactive.function.server.HandlerFunction
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import reactor.core.publisher.Mono

class ProductHandlerFilter:HandlerFilterFunction<ServerResponse,ServerResponse> {
    override fun filter(request: ServerRequest, next: HandlerFunction<ServerResponse>): Mono<ServerResponse> {
//        val path = request.path()
//        if(path == "/product/update")
//        {
//            val s = ProductUtils.getProductTokenFromServerRequest(request)
//            if(s.isEmpty())
//            {
//                return CommonResponse.code(ResponseCode.USER_NOT_SIGN_IN)
//            }
//        }
        return next.handle(request)
    }
}