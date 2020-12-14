package pers.wzr.easymall.handler

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.core.io.ClassPathResource
import org.springframework.data.domain.Example
import org.springframework.data.domain.ExampleMatcher
import org.springframework.data.domain.ExampleMatcher.GenericPropertyMatchers.exact
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import pers.wzr.easymall.cache.ProductQueryCache
import pers.wzr.easymall.dao.ProductRepository
import pers.wzr.easymall.entity.entity.Product
import pers.wzr.easymall.entity.builder.ProductBuilder
import pers.wzr.easymall.entity.property.ProductProperty
import pers.wzr.easymall.entity.validation.ProductAdd
import pers.wzr.easymall.response.Response
import pers.wzr.easymall.response.ResponseCode
import pers.wzr.easymall.util.Utils
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
        cache.fluxProduct = repository.findAll().map {
            it.imageUrl = Utils.getProductImageUrl(it.id)
            it
        }
        return Response.code(ResponseCode.PRODUCT_GET_ALL_SUCCESS)
    }

    fun getOne(request: ServerRequest): Mono<ServerResponse>
    {
        val u = repository.findById(request.pathVariable("id"))
        cache.monoProduct = u
        return u.flatMap{
            Response.code(ResponseCode.PRODUCT_GET_ONE_SUCCESS)
        }.switchIfEmpty(
            Response.code(ResponseCode.PRODUCT_GET_ONE_FAILED_NOT_FOUND)
        )
    }

    fun add(request:ServerRequest): Mono<ServerResponse>
    {
        return request.bodyToMono(ProductAdd::class.java).flatMap {
            if(validator.hasErrors(it))
                return@flatMap validator.errors()
            val product = ProductBuilder().category(it.category).name(it.name).pnm(it.pnum).price(it.price).description(it.description).build()
            repository.save(product).then(Response.code(ResponseCode.PRODUCT_ADD_SUCCESS))
        }
    }

    fun delete(request: ServerRequest):Mono<ServerResponse>
    {
        return repository.findById(request.pathVariable("id")).flatMap {
            repository.delete(it).then(Response.code(ResponseCode.PRODUCT_DELETE_SUCCESS))
        }.switchIfEmpty(
            Response.code(ResponseCode.PRODUCT_DELETE_FAILED_NOT_FOUND)
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
                p.pnum = n.pnum
                p.price = n.price
                p.imageUrl = n.imageUrl
                repository.save(p).then(Response.code(ResponseCode.PRODUCT_UPDATE_SUCCESS))
            }.switchIfEmpty(Response.code(ResponseCode.PRODUCT_UPDATE_FAILED_NOT_EXISTS))
        }
    }

    fun getByCategory(request: ServerRequest):Mono<ServerResponse>
    {
        val category = request.pathVariable("category")
        val product = ProductBuilder().category(category).build()
        val matcher = ExampleMatcher.matching()
                .withMatcher("category", exact())
                .withIgnorePaths("id","name","price","pnum","imageUrl","description")
        val p = repository.findAll(Example.of(product,matcher))
        cache.fluxProduct = p
        return Response.code(ResponseCode.PRODUCT_GET_BY_CATEGORY_SUCCESS)
    }

    fun getImageURL(request: ServerRequest):Mono<ServerResponse>
    {
        val id = request.pathVariable("id")
        return repository.findById(id).flatMap {
            Response.code(ResponseCode.PRODUCT_GET_ONE_IMAGE_URL_SUCCESS)
        }.switchIfEmpty(Response.code(ResponseCode.PRODUCT_GET_ONE_IMAGE_URL_FAILED_NOT_FOUND))
    }

    fun getImagesURL(request: ServerRequest):Mono<ServerResponse>
    {
        return repository.count().flatMap {
            if(it == 0.toLong())
                return@flatMap Response.code(ResponseCode.PRODUCT_GET_ALL_IMAGES_URL_FAILED_NOT_FOUND)
            Response.code(ResponseCode.PRODUCT_GET_ALL_IMAGES_URL_SUCCESS)
        }
    }

    fun image(request: ServerRequest):Mono<ServerResponse>
    {
        return Response.image(request.pathVariable("id"))
    }

    fun data(request: ServerRequest):Mono<ServerResponse>
    {
        return when(request.pathVariable("code")) {
            ResponseCode.PRODUCT_GET_ONE_SUCCESS -> Response.mono(cache.monoProduct, Product::class.java)
            ResponseCode.PRODUCT_GET_BY_CATEGORY_SUCCESS,
            ResponseCode.PRODUCT_GET_ALL_SUCCESS -> Response.flux(cache.fluxProduct, Product::class.java)
            else -> Response.code(ResponseCode.ERROR_GET_GATE_CODE)
        }
    }
}