package pers.wzr.easymall.router

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.function.server.*
import pers.wzr.easymall.handler.OrdersHandler

@Configuration
class OrdersRouter {
    companion object{
        private const val ORDERS = "/orders/"
        private const val GET = ORDERS + "get"
    }

    @Bean
    fun ordersRouterConfig(handler: OrdersHandler) = router {
        GET(GET,handler::get)
    }
}