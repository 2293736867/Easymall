package pers.wzr.productservice.router

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.function.server.router
import pers.wzr.productservice.handler.Handler

@Configuration
class Router {
    companion object{
        private const val GET_ALL = "get"
        private const val GET_ONE = "get/{id}"
        private const val DELETE = "delete/{id}"
        private const val UPDATE = "update"
        private const val ADD = "add"
        private const val GET_BY_CATEGORY = "get/category/{category}"
        private const val DATA = "data/{code}"
    }

    @Bean
    fun productRouter(handler:Handler) = router{
        GET(GET_ALL,handler::getAll)
        GET(GET_ONE,handler::getOne)
        DELETE(DELETE,handler::delete)
        PUT(UPDATE,handler::update)
        POST(ADD,handler::add)
        GET(GET_BY_CATEGORY,handler::getByCategory)
        GET(DATA,handler::data)
    }
}