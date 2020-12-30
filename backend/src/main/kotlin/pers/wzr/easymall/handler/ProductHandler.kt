package pers.wzr.easymall.handler

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Example
import org.springframework.data.domain.ExampleMatcher
import org.springframework.data.domain.ExampleMatcher.GenericPropertyMatchers.exact
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import pers.wzr.easymall.cache.ProductCache
import pers.wzr.easymall.dao.ProductRepository
import pers.wzr.easymall.entity.builder.ProductBuilder
import pers.wzr.easymall.entity.property.ProductProperty
import pers.wzr.easymall.entity.validation.ProductAdd
import pers.wzr.easymall.response.CommonResponse
import pers.wzr.easymall.response.ProductResponse
import pers.wzr.easymall.response.ResponseCode
import pers.wzr.easymall.util.Utils
import pers.wzr.easymall.validator.CustomValidator
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import java.util.*

@Component
class ProductHandler {
    @Autowired
    lateinit var repository: ProductRepository
    @Autowired
    lateinit var validator:CustomValidator

    private var cache = ProductCache()

    fun getDetailAll(request: ServerRequest):Mono<ServerResponse>
    {
        cache.fluxProduct = repository.findAll().flatMap { Flux.just(Utils.productResultFromProduct(it)) }
        return CommonResponse.code(ResponseCode.PRODUCT_GET_ALL_SUCCESS)
    }

    fun getDetailOne(request: ServerRequest): Mono<ServerResponse>
    {
        val u = repository.findById(request.pathVariable("id"))
        return u.flatMap{
            cache.monoProduct = Mono.just(Utils.productResultFromProduct(it))
            CommonResponse.code(ResponseCode.PRODUCT_GET_ONE_SUCCESS)
        }.switchIfEmpty(
            CommonResponse.code(ResponseCode.PRODUCT_GET_ONE_FAILED_NOT_FOUND)
        )
    }

    fun getByCategories(request: ServerRequest):Mono<ServerResponse>
    {
        cache.fluxProduct = repository.findAll().flatMap {
            return@flatMap Flux.just(Utils.productResultFromProduct(it))
        }
        return CommonResponse.code(ResponseCode.PRODUCT_GET_BY_CATEGORIES_SUCCESS)
    }

    fun getByCategory(request: ServerRequest):Mono<ServerResponse>
    {
        val category = request.pathVariable("category")
        val product = ProductBuilder().category(category).build()
        val matcher = ExampleMatcher.matching()
            .withMatcher(ProductProperty.category(), exact())
            .withIgnorePaths(*ProductProperty.other())
        val p = repository.findAll(Example.of(product,matcher))
        cache.fluxProduct = p.flatMap { Flux.just(Utils.productResultFromProduct(it)) }
        return CommonResponse.code(ResponseCode.PRODUCT_GET_BY_CATEGORY_SUCCESS)
    }

    fun getCoverImage(request: ServerRequest):Mono<ServerResponse>
    {
        println("cover image??????")
        return ProductResponse.coverImage(request.pathVariable("id"),request.pathVariable("num"))
    }

    fun getThumbnailImage(request: ServerRequest):Mono<ServerResponse>
    {
        return ProductResponse.thumbnailImage(request.pathVariable("id"),request.pathVariable("num"))
    }

    fun getDetailImage(request: ServerRequest):Mono<ServerResponse>
    {
        return ProductResponse.detailImage(request.pathVariable("id"),request.pathVariable("num"))
    }

    fun add(request:ServerRequest): Mono<ServerResponse>
    {
        return request.bodyToMono(ProductAdd::class.java).flatMap {
            if(validator.hasErrors(it))
                return@flatMap validator.errors()
            val product = ProductBuilder().category(it.category).name(it.name).pnm(it.num).price(it.price).description(it.description).build()
            repository.save(product).then(CommonResponse.code(ResponseCode.PRODUCT_ADD_SUCCESS))
        }
    }

    fun delete(request: ServerRequest):Mono<ServerResponse>
    {
        return repository.findById(request.pathVariable("id")).flatMap {
            repository.delete(it).then(CommonResponse.code(ResponseCode.PRODUCT_DELETE_SUCCESS))
        }.switchIfEmpty(
            CommonResponse.code(ResponseCode.PRODUCT_DELETE_FAILED_NOT_FOUND)
        )
    }

    fun update(request: ServerRequest):Mono<ServerResponse>
    {
        return request.bodyToMono(ProductAdd::class.java).flatMap {
            if(validator.hasErrors(it))
                return@flatMap validator.errors()
            val name = it.name
            val p = ProductBuilder().name(name).build()
            val matcher = ExampleMatcher.matching().withMatcher(ProductProperty.name(),exact()).withIgnorePaths(*ProductProperty.other())
            repository.findOne(Example.of(p,matcher)).flatMap { n->
                p.category = n.category
                p.num = n.num
                p.price = n.price
                repository.save(p).then(CommonResponse.code(ResponseCode.PRODUCT_UPDATE_SUCCESS))
            }.switchIfEmpty(CommonResponse.code(ResponseCode.PRODUCT_UPDATE_FAILED_NOT_EXISTS))
        }
    }

//    fun image(request: ServerRequest):Mono<ServerResponse>
//    {
//        return Response.image(request.pathVariable("id"))
//    }

    fun data(request: ServerRequest):Mono<ServerResponse>
    {
        return when(request.pathVariable("code")) {
            ResponseCode.PRODUCT_GET_ONE_SUCCESS -> ProductResponse.mono(cache.monoProduct)
            ResponseCode.PRODUCT_GET_BY_CATEGORIES_SUCCESS,
            ResponseCode.PRODUCT_GET_BY_CATEGORY_SUCCESS,
            ResponseCode.PRODUCT_GET_ALL_SUCCESS -> ProductResponse.flux(cache.fluxProduct)
            else -> CommonResponse.code(ResponseCode.ERROR_GET_GATE_CODE)
        }
    }
}