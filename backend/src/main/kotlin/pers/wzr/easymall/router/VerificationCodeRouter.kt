package pers.wzr.easymall.router

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.function.server.*
import pers.wzr.easymall.handler.VerificationHandler

@Configuration
class VerificationCodeRouter {
    companion object{
        private const val CODE = "/verification/code"
    }
    @Bean
    fun verificationCodeRouters(handler: VerificationHandler) = router {
        GET(CODE,handler::code)
    }
}