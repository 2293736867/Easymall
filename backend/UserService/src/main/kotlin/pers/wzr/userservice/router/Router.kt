package pers.wzr.userservice.router

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.function.server.router
import pers.wzr.userservice.handler.Handler

@Configuration
class Router {
    companion object{
        private const val TEST = "/"
    }
    @Bean
    fun routerConfig(handler: Handler) = router {
        GET(TEST,handler::test)
    }
}