package pers.wzr.easymall.router

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.function.server.*
import pers.wzr.easymall.filter.TestHandlerFilter
import pers.wzr.easymall.handler.TestHandler

@Configuration
class TestRouter {
    companion object {
        private const val TEST = "/test"
    }

    @Bean
    fun testRouterHandler(testHandler: TestHandler):RouterFunction<*> = router {
        GET(TEST,testHandler::test)
    }.filter (TestHandlerFilter())
}