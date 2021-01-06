package pers.wzr.easymall.router

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.function.server.*
import pers.wzr.easymall.handler.FavouriteHandler

@Configuration
class FavouriteRouter {
    companion object{
        private const val FAVOURITE = "/favourite/"
        private const val GET = FAVOURITE + "get"
    }

    @Bean
    fun favouriteRouterConfig(handler: FavouriteHandler) = router {
        GET(GET,handler::get)
    }
}