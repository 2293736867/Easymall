package pers.wzr.easymall.router

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.function.server.*
import pers.wzr.easymall.handler.ProductHandler

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
        private const val GET_IMAGE_URL = PRODUCT + "get/image/url/{id}"
        private const val GET_IMAGES_URL = PRODUCT + "get/image/url"
        private const val IMAGE = PRODUCT + "image/{id}"
        private const val DATA = PRODUCT + "data/{code}"
    }

    @Bean
    fun productRouterHandler(productHandler: ProductHandler) = router {
        DELETE(DELETE,productHandler::delete)
        GET(GET_ONE,productHandler::getOne)
        GET(GET_ALL,productHandler::getAll)
        PUT(UPDATE,productHandler::update)
        POST(ADD,productHandler::add)
        GET(DATA,productHandler::data)
        GET(GET_BY_CATEGORY,productHandler::getByCategory)
        GET(GET_IMAGE_URL,productHandler::getImageURL)
        GET(GET_IMAGES_URL,productHandler::getImagesURL)
        GET(IMAGE,productHandler::image)
    }
}