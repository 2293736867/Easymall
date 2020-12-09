package pers.wzr.handler

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Example
import org.springframework.data.domain.ExampleMatcher
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import pers.wzr.dao.ProductRepository
import pers.wzr.entity.Product
import pers.wzr.entity.ProductBuilder
import pers.wzr.entity.UserBuilder
import pers.wzr.response.Response
import pers.wzr.response.ResponseCode
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import reactor.core.publisher.toMono

@Component
class ProductHandler {
    @Autowired
    lateinit var repository: ProductRepository

    fun getAll(request: ServerRequest):Mono<ServerResponse>
    {
        return Response().data(repository.findAll()).code(ResponseCode.PRODUCT_GET_ALL_SUCCESS).build()
    }

    fun getOne(request: ServerRequest): Mono<ServerResponse>
    {
        return repository.findById(request.pathVariable("id")).flatMap { p->
            Response().code(ResponseCode.PRODUCT_GET_ONE_SUCCESS).data(p).build()
        }.switchIfEmpty(Response().code(ResponseCode.PRODUCT_GET_ONE_FAILED_NOT_FOUND).build())
    }

    fun add(request:ServerRequest): Mono<ServerResponse>
    {
        return request.bodyToMono(Product::class.java).flatMap { p->
            repository.save(p).then(Response().code(ResponseCode.PRODUCT_ADD_SUCCESS).build())
        }
    }

    fun delete(request: ServerRequest):Mono<ServerResponse>
    {
        return repository.findById(request.pathVariable("id"))
        .flatMap { p->
            repository.delete(p)
            .then(Response().code(ResponseCode.PRODUCT_DELETE_SUCCESS).build())
        }.switchIfEmpty(Response().code(ResponseCode.PRODUCT_DELETE_FAILED_NOT_FOUND).build())
    }

    fun update(request: ServerRequest):Mono<ServerResponse>
    {
        return request.bodyToMono(Product::class.java).flatMap { p->
            repository.save(p).then(Response().code(ResponseCode.PRODUCT_UPDATE_SUCCESS).build())
        }
    }

    fun getByCategory(request: ServerRequest):Mono<ServerResponse>
    {
        val category = request.pathVariable("category")
        val product = ProductBuilder().category(category).build()
        val matcher = ExampleMatcher.matching()
                .withMatcher("category", ExampleMatcher.GenericPropertyMatchers.exact())
                .withIgnorePaths("id","name","price","pnum","imageUrl","description")
        return Response().data(repository.findAll(Example.of(product,matcher))).code(ResponseCode.PRODUCT_GET_BY_CATEGORY_SUCCESS).build()
    }
}