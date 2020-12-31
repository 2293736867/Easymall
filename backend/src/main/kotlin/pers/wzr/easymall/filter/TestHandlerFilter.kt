package pers.wzr.easymall.filter

import org.springframework.web.reactive.function.server.HandlerFilterFunction
import org.springframework.web.reactive.function.server.HandlerFunction
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import pers.wzr.easymall.util.JWTUtils
import reactor.core.publisher.Mono

class TestHandlerFilter:HandlerFilterFunction<ServerResponse,ServerResponse> {
    override fun filter(request: ServerRequest, next: HandlerFunction<ServerResponse>): Mono<ServerResponse> {
        val session = request.session()
        return session.flatMap { t->
            val username = request.queryParam("username")
            if(!username.isPresent)
            {
                return@flatMap ServerResponse.ok().body(Mono.just("Please input usernmae"),String::class.java)
            }
            val password = request.queryParam("password")
            if(!password.isPresent)
            {
                return@flatMap ServerResponse.ok().body(Mono.just("Please input password"),String::class.java)
            }
            if(username.get() == "11" && password.get() == "22")
            {
                val token = JWTUtils.generate(username.get(),password.get())
                t.attributes["token"] = token
                println("create token:$token")
                return@flatMap next.handle(request)
            }
            ServerResponse.ok().body(Mono.just("username or password error"),String::class.java)
        }
    }
}