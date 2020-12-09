package pers.wzr.response

import org.springframework.http.MediaType
import org.springframework.web.reactive.function.server.ServerResponse
import reactor.core.publisher.Mono

class Response
{
    private val response = ResponseBody()

    fun data(data:Any): Response
    {
        response.data = data
        return this
    }

    fun code(code:Int): Response
    {
        response.code = code
        return this
    }

    fun message(message:String): Response
    {
        response.message = message
        return this
    }

    fun build(code:Int,message:String,data:Any): Mono<ServerResponse> {
        response.code = code
        response.message = message
        response.data = data
        return build()
    }

    fun build(code:Int):Mono<ServerResponse>{
        response.code = code
        return build()
    }

    fun build() = ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).body(Mono.just(response),ResponseBody::class.java)

    private class ResponseBody{
        var data = Any()
        var code = 0
        var message = ""
    }
}
