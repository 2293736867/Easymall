package pers.wzr.easymall.response

import org.springframework.web.reactive.function.server.ServerResponse
import reactor.core.publisher.Mono

data class JSONResponse<T>(
    var code:String = "",
    var data:T
){
    companion object{
        fun code(code:String):Mono<ServerResponse> =
            ServerResponse.ok().bodyValue(JSONResponse(code=code,""))

        fun <T> data(data:T):Mono<ServerResponse> =
            ServerResponse.ok().bodyValue(JSONResponse(data=data))

        fun <T> codeAndData(code:String,data:T):Mono<ServerResponse> =
            ServerResponse.ok().bodyValue(JSONResponse(code,data))
    }
}