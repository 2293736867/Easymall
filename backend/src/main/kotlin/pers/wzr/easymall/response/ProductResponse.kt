package pers.wzr.easymall.response

import org.springframework.core.io.ClassPathResource
import org.springframework.web.reactive.function.BodyInserters
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.body
import pers.wzr.easymall.entity.validation.ProductReturn
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

class ProductResponse{

    companion object{
        fun coverImage(id:String,num:String):Mono<ServerResponse>{
            println("cover image")
            return image(id,num,"cover")
        }

        fun thumbnailImage(id:String,num: String):Mono<ServerResponse>{
            return image(id,num,"thumbnail")
        }

        fun detailImage(id:String,num: String):Mono<ServerResponse>{
            return image(id,num,"detail")
        }

        private fun image(id:String,num:String,path:String):Mono<ServerResponse>{
            println(ClassPathResource("img/$id/$path/$num.jpg"))
            return ServerResponse.ok().body(BodyInserters.fromValue(ClassPathResource("img/$id/$path/$num.jpg")))
        }

        fun mono(t:Mono<ProductReturn>): Mono<ServerResponse>
        {
            return ServerResponse.ok().body(t)
        }

        fun flux(t:Flux<ProductReturn>): Mono<ServerResponse>
        {
            return ServerResponse.ok().body(t)
        }
    }
}
