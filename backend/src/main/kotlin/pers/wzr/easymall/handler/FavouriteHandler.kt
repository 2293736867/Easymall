package pers.wzr.easymall.handler

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import pers.wzr.easymall.dao.FavouriteRepository
import pers.wzr.easymall.response.JSONResponse
import pers.wzr.easymall.validator.CustomValidator
import reactor.core.publisher.Mono

@Component
class FavouriteHandler {
    @Autowired
    lateinit var repository: FavouriteRepository
    @Autowired
    lateinit var validator: CustomValidator

    fun get(request: ServerRequest): Mono<ServerResponse> = JSONResponse.code("test")
}