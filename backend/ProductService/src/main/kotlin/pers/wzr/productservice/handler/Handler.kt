package pers.wzr.productservice.handler

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import pers.wzr.productservice.dao.Repository
import pers.wzr.productservice.entity.Product
import reactor.core.publisher.Mono

@Component
class Handler {
    @Autowired
    lateinit var repository: Repository

    fun test(request:ServerRequest): Mono<ServerResponse>
    = ServerResponse.ok().body(Mono.just("product test"),String::class.java)

    fun getAll(request: ServerRequest):Mono<ServerResponse>
    {
        return ServerResponse.ok().body(repository.findAll(),Product::class.java)
//        return repository.findById("111").flatMap { t->
//            ServerResponse.ok().body(t,Product::class.java)
//        }.switchIfEmpty(ServerResponse.notFound().build())
    }
}