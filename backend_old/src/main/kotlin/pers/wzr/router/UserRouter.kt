package pers.wzr.router

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.function.server.*
import pers.wzr.handler.UserHandler

@Configuration
class UserRouter {
    companion object {
        private const val USER = "/user/"
        const val SIGN_IN = USER + "sign/in"
        const val SIGN_UP = USER + "sign/up"
        const val DELETE = USER + "delete/{id}"
        const val GET_ALL= USER + "get"
        const val GET_BY_ID = USER + "get/{id}"
        const val CHECK_USERNAME_EXISTS = USER + "check/username/{username}"
    }

    @Bean
    fun userRouterHandler(userHandle: UserHandler):RouterFunction<ServerResponse>
    {
        return RouterFunctions
        .route(RequestPredicates.DELETE(DELETE),userHandle::delete)
        .andRoute(RequestPredicates.POST(SIGN_IN),userHandle::signIn)
        .andRoute(RequestPredicates.POST(SIGN_UP),userHandle::signUp)
        .andRoute(RequestPredicates.GET(GET_ALL),userHandle::getAll)
        .andRoute(RequestPredicates.GET(GET_BY_ID),userHandle::getById)
        .andRoute(RequestPredicates.GET(CHECK_USERNAME_EXISTS),userHandle::checkUsernameExists)
    }
}