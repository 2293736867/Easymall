package pers.wzr.easymall.response

import org.springframework.core.io.ClassPathResource
import org.springframework.web.reactive.function.BodyInserters
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse

class ProductResponse {

    companion object {

        fun coverImage(request: ServerRequest) = image(request, "cover")

        fun thumbnailImage(request: ServerRequest) = image(request, "thumbnail")

        fun detailImage(request: ServerRequest) = image(request, "detail")

        private fun image(request: ServerRequest, path: String) =
            ServerResponse.ok().body(
                BodyInserters.fromValue(
                    ClassPathResource("img/${request.pathVariable("id")}/$path/${request.pathVariable("num")}.jpg")
                )
            )
    }
}
