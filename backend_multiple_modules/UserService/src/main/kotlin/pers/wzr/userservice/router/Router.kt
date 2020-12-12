package pers.wzr.userservice.router

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.function.server.router
import pers.wzr.userservice.handler.Handler

@Configuration
class Router {
    companion object{
        private const val SIGN_IN = "sign/in"
        private const val SIGN_UP = "sign/up"
        private const val DELETE = "delete"
        private const val GET_ALL = "get"
        private const val GET_BY_ID = "get/id/{id}"
        private const val CHECK_USERNAME_EXISTS = "check/username/{username}"
        private const val DATA = "data/{code}"
    }

    @Bean
    fun routerConfig(handler: Handler) = router {
        POST(SIGN_IN,handler::signIn)
        POST(SIGN_UP,handler::signUp)
        GET(GET_ALL,handler::getAll)
        GET(GET_BY_ID,handler::getById)
        GET(CHECK_USERNAME_EXISTS,handler::checkUsernameExists)
        DELETE(DELETE,handler::delete)
        GET(DATA,handler::data)
    }
}