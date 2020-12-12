package pers.wzr.gateway.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.session.config.annotation.web.server.EnableSpringWebSession
import org.springframework.session.ReactiveMapSessionRepository
import java.util.concurrent.ConcurrentHashMap

//@Configuration
//@EnableSpringWebSession
class SessionConfiguration {
//    @Bean
    fun reactiveSessionRepository() = ReactiveMapSessionRepository(ConcurrentHashMap())
}