package pers.wzr.router

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.function.server.*
import pers.wzr.handler.UserHandler

@Configuration
class UserRouter {
    companion object {
        const val SIGN_IN = "/sign/in"
        const val SIGN_UP = "/sign/up"
        const val DELETE = "/delete/{id}"
        const val SELECT_ALL="/select"
        const val SELECT_BY_ID="/select/{id}"
        const val CHECK_USERNAME_EXISTS="/check/username/{username}"
    }

    @Bean
    fun userRouterHandler(userHandle: UserHandler):RouterFunction<ServerResponse>
    {
        return RouterFunctions
        .route(RequestPredicates.DELETE(DELETE),userHandle::delete)
        .andRoute(RequestPredicates.POST(SIGN_IN),userHandle::signIn)
        .andRoute(RequestPredicates.POST(SIGN_UP),userHandle::signUp)
        .andRoute(RequestPredicates.GET(SELECT_ALL),userHandle::selectAll)
        .andRoute(RequestPredicates.GET(SELECT_BY_ID),userHandle::selectById)
        .andRoute(RequestPredicates.GET(CHECK_USERNAME_EXISTS),userHandle::checkUsernameExists)
    }
}