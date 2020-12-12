package pers.wzr.productservice.handler

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Example
import org.springframework.data.domain.ExampleMatcher
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import pers.wzr.commons.response.Response
import pers.wzr.commons.response.ResponseCode
import pers.wzr.productservice.cache.QueryCache
import pers.wzr.productservice.dao.Repository
import pers.wzr.productservice.entity.Product
import pers.wzr.productservice.entity.ProductBuilder
import reactor.core.publisher.Mono

@Component
class Handler {
    @Autowired
    lateinit var repository: Repository
    private var cache = QueryCache()

    fun getAll(request: ServerRequest):Mono<ServerResponse>
    {
        cache.fluxProduct = repository.findAll()
        return Response.build(ResponseCode.PRODUCT_GET_ALL_SUCCESS)
    }

    fun getOne(request: ServerRequest): Mono<ServerResponse>
    {
        val u = repository.findById(request.pathVariable("id"))
        return u.flatMap {
            cache.monoProduct = u
            Response.build(ResponseCode.PRODUCT_GET_ONE_SUCCESS)
        }.switchIfEmpty(Response.build(ResponseCode.PRODUCT_GET_ONE_FAILED_NOT_FOUND))
    }

    fun add(request:ServerRequest): Mono<ServerResponse>
    {
        return request.bodyToMono(Product::class.java).flatMap { p->
            repository.save(p).then(Response.build(ResponseCode.PRODUCT_ADD_SUCCESS))
        }
    }

    fun delete(request: ServerRequest):Mono<ServerResponse>
    {
        return repository.findById(request.pathVariable("id")).flatMap { p->
            repository.delete(p).then(Response.build(ResponseCode.PRODUCT_DELETE_SUCCESS))
        }.switchIfEmpty(Response.build(ResponseCode.PRODUCT_DELETE_FAILED_NOT_FOUND))
    }

    fun update(request: ServerRequest):Mono<ServerResponse>
    {
        return request.bodyToMono(Product::class.java).flatMap { p->
            repository.save(p).then(Response.build(ResponseCode.PRODUCT_UPDATE_SUCCESS))
        }
    }

    fun getByCategory(request: ServerRequest):Mono<ServerResponse>
    {
        val category = request.pathVariable("category")
        val product = ProductBuilder().category(category).build()
        val matcher = ExampleMatcher.matching()
            .withMatcher("category", ExampleMatcher.GenericPropertyMatchers.exact())
            .withIgnorePaths("id","name","price","pnum","imageUrl","description")
        cache.fluxProduct = repository.findAll(Example.of(product,matcher))
        return Response.build(ResponseCode.PRODUCT_GET_BY_CATEGORY_SUCCESS)
    }

    fun data(request: ServerRequest):Mono<ServerResponse>
    {
        val code = request.pathVariable("code")
        if(code == ResponseCode.PRODUCT_GET_ONE_SUCCESS)
            return Response.build(cache.monoProduct,Product::class.java)
        if(code == ResponseCode.PRODUCT_GET_ALL_SUCCESS || code == ResponseCode.PRODUCT_GET_BY_CATEGORY_SUCCESS)
            return Response.build(cache.fluxProduct,Product::class.java)
        return Response.build(ResponseCode.ERROR_CODE)
    }
}