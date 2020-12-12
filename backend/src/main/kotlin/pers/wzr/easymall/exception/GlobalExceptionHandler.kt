package pers.wzr.easymall.exception

import com.fasterxml.jackson.core.JsonProcessingException
import com.fasterxml.jackson.databind.ObjectMapper
import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger
import org.springframework.boot.web.reactive.error.ErrorWebExceptionHandler
import org.springframework.http.MediaType
import org.springframework.stereotype.Component
import org.springframework.web.server.ResponseStatusException
import org.springframework.web.server.ServerWebExchange
import reactor.core.publisher.Mono

@Component
class GlobalExceptionHandler:ErrorWebExceptionHandler{
    private val log: Logger = LogManager.getLogger(GlobalExceptionHandler::class.java)
    private val objectMapper = ObjectMapper()

    override fun handle(exchange: ServerWebExchange, ex: Throwable): Mono<Void> {
        val response = exchange.response
        if(response.isCommitted)
            return Mono.error(ex)
        response.headers.contentType = MediaType.APPLICATION_JSON
        if(ex is ResponseStatusException)
        {
            response.statusCode = ex.status
        }
        return response.writeWith(Mono.fromSupplier {
            val bufferFactory = response.bufferFactory()
            try {
                return@fromSupplier bufferFactory.wrap(objectMapper.writeValueAsBytes(ex.message))
            }
            catch (e: JsonProcessingException)
            {
                log.warn("Error writing response",ex)
                return@fromSupplier bufferFactory.wrap(ByteArray(0))
            }
        })
    }
}