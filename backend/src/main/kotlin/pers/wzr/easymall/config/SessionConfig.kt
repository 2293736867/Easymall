package pers.wzr.easymall.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.session.ReactiveMapSessionRepository
import org.springframework.session.config.annotation.web.server.EnableSpringWebSession
import java.util.concurrent.ConcurrentHashMap

@Configuration
@EnableSpringWebSession
class SessionConfig {
    @Bean
    fun reactiveSessionRepository() = ReactiveMapSessionRepository(ConcurrentHashMap())
}