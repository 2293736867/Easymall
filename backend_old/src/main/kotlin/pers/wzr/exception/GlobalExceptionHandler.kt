package pers.wzr.exception

import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class GlobalExceptionHandler{
    val log: Logger = LogManager.getLogger(GlobalExceptionHandler::class.java)

    @ExceptionHandler(Exception::class)
    fun handle(e:Exception)
    {
        e.printStackTrace()
        log.info(e.stackTraceToString())
    }
}