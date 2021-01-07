package pers.wzr.easymall.handler

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Example
import org.springframework.data.domain.ExampleMatcher
import org.springframework.data.domain.ExampleMatcher.GenericPropertyMatchers.exact
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import pers.wzr.easymall.dao.ShoppingCardRepository
import pers.wzr.easymall.entity.entity.Product
import pers.wzr.easymall.entity.entity.ShoppingCard
import pers.wzr.easymall.entity.property.ProductProperty
import pers.wzr.easymall.entity.property.ShoppingCardProperty
import pers.wzr.easymall.entity.util.UserUtils
import pers.wzr.easymall.response.JSONResponse
import pers.wzr.easymall.response.ResponseCode
import pers.wzr.easymall.util.JWTUtils
import pers.wzr.easymall.validator.CustomValidator
import pers.wzr.easymall.validator.ValidationGroup
import reactor.core.publisher.Mono

@Component
class ShoppingCardHandler {
    @Autowired
    lateinit var repository: ShoppingCardRepository

    @Autowired
    lateinit var validator: CustomValidator

    fun get(request: ServerRequest) = repository.findAll(
        Example.of(
            ShoppingCard(userId = JWTUtils.getUserIdFromServerRequest(request)),
            ExampleMatcher.matching().withMatcher(
                ShoppingCardProperty.userId(),
                exact()
            ).withIgnorePaths(*ShoppingCardProperty.other())
        )
    ).collectList().flatMap {
        JSONResponse.codeAndData(ResponseCode.SHOPPING_CARD_GET_BY_USER_ID_SUCCESS, it)
    }.switchIfEmpty(
        JSONResponse.code(ResponseCode.SHOPPING_CARD_GET_BY_USER_ID_FAILED_EMPTY)
    )

    fun add(request: ServerRequest) = request.bodyToMono(ShoppingCard::class.java).flatMap {
        it.userId = JWTUtils.getUserIdFromServerRequest(request)
        if (validator.hasErrors(it))
            return@flatMap validator.errors()
        return@flatMap repository.findAll(
            Example.of(
                ShoppingCard(userId = it.userId, productId = it.productId),
                ExampleMatcher.matching()
                    .withMatcher(ShoppingCardProperty.userId(), exact())
                    .withMatcher(ShoppingCardProperty.productId(), exact())
                    .withIgnorePaths(*ShoppingCardProperty.other())
            )
        ).collectList().flatMap { old ->
            it.id = old[0].id
            it.num += old[0].num
            repository.save(it).then(JSONResponse.code(ResponseCode.SHOPPING_CARD_ADD_SUCCESS))
        }.switchIfEmpty(
            repository.save(it).then(
                JSONResponse.code(ResponseCode.SHOPPING_CARD_ADD_SUCCESS)
            ).switchIfEmpty(
                JSONResponse.code(ResponseCode.SHOPPING_CARD_ADD_FAILED)
            )
        )
    }

    fun delete(request: ServerRequest) = repository.findAll(
        Example.of(
            ShoppingCard(
                userId = JWTUtils.getUserIdFromServerRequest(request),
                productId = request.pathVariable("productId")
            ),
            ExampleMatcher.matching()
                .withMatcher(ShoppingCardProperty.userId(), exact())
                .withMatcher(ShoppingCardProperty.productId(), exact())
                .withIgnorePaths(*ShoppingCardProperty.other())
        )
    ).collectList().filter {
        it.size > 0
    }.flatMap {
        repository.deleteById(it[0].id).then(JSONResponse.code(ResponseCode.SHOPPING_CARD_DELETE_SUCCESS))
    }.switchIfEmpty(
        JSONResponse.code(ResponseCode.SHOPPING_CARD_DELETE_FAILED_NOT_FOUND)
    )

    fun update(request: ServerRequest) = request.bodyToMono(ShoppingCard::class.java).flatMap {
        if (validator.hasErrors(it))
            return@flatMap validator.errors()
        it.userId = JWTUtils.getUserIdFromServerRequest(request)
        return@flatMap repository.findAll(
            Example.of(
                ShoppingCard(userId = it.userId, productId = it.productId),
                ExampleMatcher.matching()
                    .withMatcher(ShoppingCardProperty.userId(), exact())
                    .withMatcher(ShoppingCardProperty.productId(), exact())
                    .withIgnorePaths(*ShoppingCardProperty.other())
            )
        ).collectList().filter { list ->
            list.size > 0
        }.flatMap { l ->
            it.id = l[0].id
            repository.save(it).then(JSONResponse.code(ResponseCode.SHOPPING_CARD_UPDATE_SUCCESS))
        }.switchIfEmpty(
            JSONResponse.code(ResponseCode.SHOPPING_CARD_UPDATE_FAILED_NOT_EXIST)
        )
    }
}