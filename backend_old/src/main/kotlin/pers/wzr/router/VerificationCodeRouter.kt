package pers.wzr.router

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.function.server.RequestPredicates
import org.springframework.web.reactive.function.server.RouterFunction
import org.springframework.web.reactive.function.server.RouterFunctions
import org.springframework.web.reactive.function.server.ServerResponse
import pers.wzr.handler.VerificationHandler

@Configuration
class VerificationCodeRouter {
    @Bean
    fun verificationCodeRouters(handler:VerificationHandler):RouterFunction<ServerResponse>{
        return RouterFunctions
        .route(RequestPredicates.GET("/verification/code"),handler::code)
    }
}