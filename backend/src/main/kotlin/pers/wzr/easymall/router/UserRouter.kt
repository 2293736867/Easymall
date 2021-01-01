package pers.wzr.easymall.router

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.function.server.router
import pers.wzr.easymall.filter.UserHandlerFilter
import pers.wzr.easymall.handler.UserHandler

@Configuration
class UserRouter {
    companion object{
        private const val USER = "/user/"
        private const val SIGN_IN = USER + "sign/in"
        private const val SIGN_UP = USER + "sign/up"
        private const val DELETE = USER + "delete/{id}"
        private const val UPDATE = USER + "update"
        private const val GET_ALL = USER + "get"
//        private const val GET_BY_ID = USER + "get/{id}"
        private const val GET_EMAIL = USER + "get/email"
        private const val GET_NICKNAME = USER + "get/nickname"
        private const val CHECK_USERNAME_EXISTS = USER + "check/username/{username}"
        private const val DATA = USER + "data/{code}"
    }

    @Bean
    fun userRouterConfig(handler: UserHandler) = router {
        GET(DATA,handler::data)
        GET(GET_ALL,handler::getAll)
//        GET(GET_BY_ID,handler::getById)
        GET(GET_EMAIL,handler::getEmail)
        GET(GET_NICKNAME,handler::getNickname)
        GET(CHECK_USERNAME_EXISTS,handler::checkUsernameExists)

        POST(SIGN_IN,handler::signIn)
        POST(SIGN_UP,handler::signUp)
        DELETE(DELETE,handler::delete)
        PUT(UPDATE,handler::update)
    }.filter(UserHandlerFilter())
}