package pers.wzr.verificationcode.router

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.function.server.router
import pers.wzr.verificationcode.handler.Handler

@Configuration
class Router {
    @Bean
    fun routerConfig(handler:Handler) = router {
        GET("/verfication/code",handler::get)
    }
}