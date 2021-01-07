package pers.wzr.easymall.router

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.function.server.*
import pers.wzr.easymall.filter.ProductHandlerFilter
import pers.wzr.easymall.handler.ProductHandler

@Configuration
class ProductRouter {
    companion object {
        private const val PRODUCT = "/product/"

        private const val GET_DETAIL_ALL = PRODUCT + "get/detail"
        private const val GET_DETAIL_ONE = PRODUCT + "get/detail/{id}"
        private const val GET_DETAILS = PRODUCT + "get/details"

        private const val GET_BY_CATEGORIES = PRODUCT + "get/category"
        private const val GET_BY_CATEGORY = PRODUCT + "get/category/{category}"

        private const val GET_COVER_IMAGE = PRODUCT + "get/image/{id}/cover/{num}"
        private const val GET_THUMBNAIL_IMAGE = PRODUCT + "get/image/{id}/thumbnail/{num}"
        private const val GET_DETAIL_IMAGE = PRODUCT + "get/image/{id}/detail/{num}"

        private const val DELETE = PRODUCT + "delete/{id}"
        private const val UPDATE = PRODUCT + "update/{id}"
        private const val ADD = PRODUCT + "add"
    }

    @Bean
    fun productRouterHandler(productHandler: ProductHandler) = router {
        GET(GET_DETAIL_ONE,productHandler::getDetailOne)
        GET(GET_DETAIL_ALL,productHandler::getDetailAll)
        GET(GET_BY_CATEGORIES,productHandler::getByCategories)
        GET(GET_BY_CATEGORY,productHandler::getByCategory)
        GET(GET_COVER_IMAGE,productHandler::getCoverImage)
        GET(GET_THUMBNAIL_IMAGE,productHandler::getThumbnailImage)
        GET(GET_DETAIL_IMAGE,productHandler::getDetailImage)
        POST(GET_DETAILS,productHandler::getDetails)

        DELETE(DELETE,productHandler::delete)
        PUT(UPDATE,productHandler::update)
        POST(ADD,productHandler::add)
    }.filter(ProductHandlerFilter())
}