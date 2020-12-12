package pers.wzr.easymall.handler

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Example
import org.springframework.data.domain.ExampleMatcher
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import pers.wzr.easymall.cache.ProductQueryCache
import pers.wzr.easymall.dao.ProductRepository
import pers.wzr.easymall.entity.entity.Product
import pers.wzr.easymall.entity.builder.ProductBuilder
import pers.wzr.easymall.exception.TestException
import pers.wzr.easymall.response.Response
import pers.wzr.easymall.response.ResponseCode
import pers.wzr.easymall.validator.CustomValidator
import reactor.core.publisher.Mono

@Component
class ProductHandler {
    @Autowired
    lateinit var repository: ProductRepository
    @Autowired
    lateinit var validator:CustomValidator

    private var cache = ProductQueryCache()

    fun getAll(request: ServerRequest):Mono<ServerResponse>
    {
        cache.fluxProduct = repository.findAll()
        return Response.build(ResponseCode.PRODUCT_GET_ALL_SUCCESS)
    }

    fun getOne(request: ServerRequest): Mono<ServerResponse>
    {
        val u = repository.findById(request.pathVariable("id"))
        cache.monoProduct = u
        return u.flatMap{
            Response.build(ResponseCode.PRODUCT_GET_ONE_SUCCESS)
        }.switchIfEmpty(
            Response.build(ResponseCode.PRODUCT_GET_ONE_FAILED_NOT_FOUND)
        )
    }

    fun add(request:ServerRequest): Mono<ServerResponse>
    {
        return request.bodyToMono(Product::class.java).flatMap {
            if(validator.hasErrors(it))
                return@flatMap validator.errors()
            repository.save(it).then(Response.build(ResponseCode.PRODUCT_ADD_SUCCESS))
        }
    }

    fun delete(request: ServerRequest):Mono<ServerResponse>
    {
        return repository.findById(request.pathVariable("id")).flatMap {
            repository.delete(it).then(Response.build(ResponseCode.PRODUCT_DELETE_SUCCESS))
        }.switchIfEmpty(
            Response.build(ResponseCode.PRODUCT_DELETE_FAILED_NOT_FOUND)
        )
    }

    fun update(request: ServerRequest):Mono<ServerResponse>
    {
        return request.bodyToMono(Product::class.java).flatMap {
            repository.save(it).then(Response.build(ResponseCode.PRODUCT_UPDATE_SUCCESS))
        }
    }

    fun getByCategory(request: ServerRequest):Mono<ServerResponse>
    {
        val category = request.pathVariable("category")
        val product = ProductBuilder().category(category).build()
        val matcher = ExampleMatcher.matching()
                .withMatcher("category", ExampleMatcher.GenericPropertyMatchers.exact())
                .withIgnorePaths("id","name","price","pnum","imageUrl","description")
        val p = repository.findAll(Example.of(product,matcher))
        cache.fluxProduct = p
        return Response.build(ResponseCode.PRODUCT_GET_BY_CATEGORY_SUCCESS)
    }

    fun data(request: ServerRequest):Mono<ServerResponse>
    {
        return when(request.pathVariable("code")) {
            ResponseCode.PRODUCT_GET_ONE_SUCCESS -> Response.build(cache.monoProduct, Product::class.java)
            ResponseCode.PRODUCT_GET_BY_CATEGORY_SUCCESS,
            ResponseCode.PRODUCT_GET_ALL_SUCCESS -> Response.build(cache.fluxProduct, Product::class.java)
            else -> Response.build(ResponseCode.ERROR_GET_GATE_CODE)
        }
    }
}