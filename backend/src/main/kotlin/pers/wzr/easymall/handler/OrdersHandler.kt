package pers.wzr.easymall.handler

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import pers.wzr.easymall.dao.OrdersRepository
import pers.wzr.easymall.response.JSONResponse
import pers.wzr.easymall.validator.CustomValidator
import reactor.core.publisher.Mono

@Component
class OrdersHandler {
    @Autowired
    lateinit var repository: OrdersRepository
    @Autowired
    lateinit var validator: CustomValidator

    fun get(request: ServerRequest): Mono<ServerResponse> = JSONResponse.code("test")
}