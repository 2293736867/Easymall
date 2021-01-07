package pers.wzr.easymall.handler

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Example
import org.springframework.data.domain.ExampleMatcher
import org.springframework.data.domain.ExampleMatcher.GenericPropertyMatchers.exact
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.ServerRequest
import pers.wzr.easymall.dao.ProductRepository
import pers.wzr.easymall.entity.entity.Product
import pers.wzr.easymall.entity.property.ProductProperty
import pers.wzr.easymall.entity.request.ProductIds
import pers.wzr.easymall.entity.util.ProductUtils
import pers.wzr.easymall.response.JSONResponse
import pers.wzr.easymall.response.ProductResponse
import pers.wzr.easymall.response.ResponseCode
import pers.wzr.easymall.validator.CustomValidator
import pers.wzr.easymall.validator.ValidationGroup
import java.util.*

@Component
class ProductHandler {
    @Autowired
    lateinit var repository: ProductRepository

    @Autowired
    lateinit var validator: CustomValidator

    fun getDetails(request: ServerRequest) = request.bodyToMono(ProductIds::class.java).flatMap {
//        println(it)
        it.id.forEach { _ -> println()}
//        for (i in it.split(","))
//            println(i)
        repository.findById(it.id[0])
    }.flatMap {
//        println(it.size)
        return@flatMap JSONResponse.codeAndData(ResponseCode.PRODUCT_GET_DETAILS_SUCCESS,it)
    }.switchIfEmpty(
        JSONResponse.code(ResponseCode.PRODUCT_GET_DETAILS_NOT_FOUND)
    )

    fun getDetailAll(request: ServerRequest) = repository.findAll().collectList().flatMap {
        JSONResponse.codeAndData(ResponseCode.PRODUCT_GET_ALL_SUCCESS, it)
    }.switchIfEmpty(
        JSONResponse.code(ResponseCode.PRODUCT_GET_ALL_FAILED_EMPTY)
    )

    fun getDetailOne(request: ServerRequest) = repository.findById(request.pathVariable("id")).flatMap {
        JSONResponse.codeAndData(ResponseCode.PRODUCT_GET_ONE_SUCCESS, it)
    }.switchIfEmpty(
        JSONResponse.code(ResponseCode.PRODUCT_GET_ONE_FAILED_NOT_FOUND)
    )

    fun getByCategories(request: ServerRequest) = repository.findAll().map {
        ProductUtils.productResultFromProduct(it)
    }.collectList().flatMap{
        JSONResponse.codeAndData(ResponseCode.PRODUCT_GET_BY_CATEGORIES_SUCCESS,it)
    }.switchIfEmpty(
        JSONResponse.code(ResponseCode.PRODUCT_GET_BY_CATEGORIES_FAILED_EMPTY)
    )

    fun getByCategory(request: ServerRequest) = repository.findAll(
        Example.of(
            Product(category = request.pathVariable("category")),
            ExampleMatcher.matching().withMatcher(ProductProperty.category(), exact()).withIgnorePaths(*ProductProperty.other())
        )
    ).map {
        ProductUtils.productResultFromProduct(it)
    }.collectList().filter{
        it.size > 0
    }.flatMap {
        JSONResponse.codeAndData(ResponseCode.PRODUCT_GET_BY_CATEGORY_SUCCESS,it)
    }.switchIfEmpty(
        JSONResponse.code(ResponseCode.PRODUCT_GET_BY_CATEGORY_FAILED_NOT_FOUND)
    )

    fun getCoverImage(request: ServerRequest) = ProductResponse.coverImage(request)

    fun getThumbnailImage(request: ServerRequest) = ProductResponse.thumbnailImage(request)

    fun getDetailImage(request: ServerRequest) = ProductResponse.detailImage(request)

    fun add(request: ServerRequest) = request.bodyToMono(Product::class.java).flatMap {
        if (validator.hasErrors(it, ValidationGroup.ProductAdd::class.java))
            return@flatMap validator.errors()
        repository.save(it).then(
            JSONResponse.code(ResponseCode.PRODUCT_ADD_SUCCESS)
        ).switchIfEmpty(
            JSONResponse.code(ResponseCode.PRODUCT_ADD_FAILED)
        )
    }

    fun delete(request: ServerRequest) = repository.findById(request.pathVariable("id")).flatMap {
        repository.deleteById(request.pathVariable("id")).then(JSONResponse.code(ResponseCode.PRODUCT_DELETE_SUCCESS))
    }.switchIfEmpty(
        JSONResponse.code(ResponseCode.PRODUCT_DELETE_FAILED_NOT_FOUND)
    )

    fun update(request: ServerRequest) = request.bodyToMono(Product::class.java).flatMap {
        if (validator.hasErrors(it,ValidationGroup.ProductUpdate::class.java))
            return@flatMap validator.errors()
        val id = request.pathVariable("id")
        return@flatMap repository.findById(id).flatMap { _ ->
            it.id = id
            repository.save(it).then(JSONResponse.code(ResponseCode.PRODUCT_UPDATE_SUCCESS))
        }.switchIfEmpty(
            JSONResponse.code(ResponseCode.PRODUCT_UPDATE_FAILED_NOT_EXISTS)
        )
    }
}