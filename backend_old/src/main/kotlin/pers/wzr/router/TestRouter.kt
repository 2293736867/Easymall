package pers.wzr.router

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.function.server.*
import pers.wzr.filter.TestHandlerFilter
import pers.wzr.handler.TestHandler
import pers.wzr.handler.UserHandler

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