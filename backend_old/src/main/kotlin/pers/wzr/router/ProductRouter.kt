package pers.wzr.router

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.function.server.*
import pers.wzr.handler.ProductHandler

@Configuration
class ProductRouter {
    companion object {
        private const val PRODUCT = "/product/"
        private const val GET_ALL = PRODUCT + "get"
        private const val GET_ONE = PRODUCT + "get/{id}"
        private const val DELETE = PRODUCT + "delete/{id}"
        private const val UPDATE = PRODUCT + "update"
        private const val ADD = PRODUCT + "add"
        private const val GET_BY_CATEGORY = PRODUCT + "get/category/{category}"
    }

    @Bean
    fun productRouterHandler(productHandler: ProductHandler):RouterFunction<ServerResponse>
    {
        return RouterFunctions
        .route(RequestPredicates.DELETE(DELETE),productHandler::delete)
        .andRoute(RequestPredicates.GET(GET_ONE),productHandler::getOne)
        .andRoute(RequestPredicates.GET(GET_ALL),productHandler::getAll)
        .andRoute(RequestPredicates.PUT(UPDATE),productHandler::update)
        .andRoute(RequestPredicates.POST(ADD),productHandler::add)
                .andRoute(RequestPredicates.GET(GET_BY_CATEGORY),productHandler::getByCategory)
//        .andRoute(RequestPredicates.POST(SIGN_IN),userHandle::signIn)
//        .andRoute(RequestPredicates.POST(SIGN_UP),userHandle::signUp)
//        .andRoute(RequestPredicates.GET(SELECT_ALL),userHandle::selectAll)
//        .andRoute(RequestPredicates.GET(SELECT_BY_ID),userHandle::selectById)
//        .andRoute(RequestPredicates.GET(CHECK_USERNAME_EXISTS),userHandle::checkUsernameExists)
    }
}