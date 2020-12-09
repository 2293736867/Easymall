package pers.wzr.productservice.router

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.function.server.RequestPredicates
import org.springframework.web.reactive.function.server.RouterFunctions
import org.springframework.web.reactive.function.server.router
import pers.wzr.productservice.handler.Handler

@Configuration
class Router {
    companion object{
        private const val GET_ALL = "get"
        private const val GET_ONE = "get/{id}"
        private const val TEST = "/"
    }

    @Bean
    fun productRouter(handler:Handler) = router{
        GET(TEST,handler::test)
        GET(GET_ALL,handler::getAll)
    }
}