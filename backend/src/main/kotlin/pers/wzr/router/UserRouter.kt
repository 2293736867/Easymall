package pers.wzr.router

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.function.server.*
import pers.wzr.handler.UserHandler

@Configuration
class UserRouter {
    companion object {
        const val SAVE = "/save"
        const val LOGIN = "/login"
        const val DELETE = "/delete/{id}"
        const val SELECT_ALL="/select"
        const val SELECT_BY_ID="/select/{id}"
    }

    @Bean
    fun userRouterHandler(userHandle: UserHandler):RouterFunction<ServerResponse>
    {
        return RouterFunctions
        .route(RequestPredicates.POST(SAVE),userHandle::save)
        .andRoute(RequestPredicates.DELETE(DELETE),userHandle::delete)
        .andRoute(RequestPredicates.POST(LOGIN),userHandle::login)
        .andRoute(RequestPredicates.GET(SELECT_ALL),userHandle::selectAll)
        .andRoute(RequestPredicates.GET(SELECT_BY_ID),userHandle::selectById)
    }
}