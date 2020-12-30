package pers.wzr.easymall.handler

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import pers.wzr.easymall.cache.ShoppingCardCache
import pers.wzr.easymall.dao.ShoppingCardRepository
import pers.wzr.easymall.response.CommonResponse
import pers.wzr.easymall.response.ResponseCode
import pers.wzr.easymall.response.ShoppingCardResponse
import pers.wzr.easymall.validator.CustomValidator
import reactor.core.publisher.Mono

//TODO
@Component
class ShoppingCardHandler {
    @Autowired
    lateinit var repository: ShoppingCardRepository
    @Autowired
    lateinit var validator: CustomValidator

    private val cache = ShoppingCardCache()

    fun delete(request: ServerRequest):Mono<ServerResponse>
    {
        return repository.findById(request.pathVariable("id"))
        .flatMap {
            repository.delete(it).then(CommonResponse.code(ResponseCode.USER_DELETE_SUCCESS))
        }.switchIfEmpty(
            CommonResponse.code(ResponseCode.USER_DELETE_FAILED_NOT_FOUND)
        )
    }

    fun get(request: ServerRequest):Mono<ServerResponse>
    {
        cache.fluxShoppingCard = repository.findAll()
        return CommonResponse.code(ResponseCode.USER_GET_ALL_SUCCESS)
    }

    fun data(request: ServerRequest):Mono<ServerResponse>
    {
        return when(request.pathVariable("code")){
            ResponseCode.USER_GET_ONE_SUCCESS -> ShoppingCardResponse.mono(cache.monoShoppingCard)
            ResponseCode.USER_GET_ALL_SUCCESS -> ShoppingCardResponse.flux(cache.fluxShoppingCard)
            else -> CommonResponse.code(ResponseCode.ERROR_GET_GATE_CODE)
        }
    }
}