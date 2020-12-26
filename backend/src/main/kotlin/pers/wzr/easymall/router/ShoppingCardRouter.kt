package pers.wzr.easymall.router

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.function.server.*
import pers.wzr.easymall.handler.ShoppingCardHandler

@Configuration
class ShoppingCardRouter {
    companion object{
        private const val SHOPPING_CARD = "/shopping/card/"
        private const val DELETE = SHOPPING_CARD + "delete/{id}"
        private const val UPDATE = SHOPPING_CARD + "update"
        private const val GET = SHOPPING_CARD + "get"
        private const val ADD = SHOPPING_CARD + "add"
        private const val DATA = SHOPPING_CARD + "data/{code}"
    }

    @Bean
    fun routerConfig(handler: ShoppingCardHandler) = router {
        POST(ADD,handler::add)
        GET(GET,handler::get)
        DELETE(DELETE,handler::delete)
        PUT(UPDATE,handler::update)
        GET(DATA,handler::data)
    }
}