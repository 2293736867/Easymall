package pers.wzr.easymall.config

import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.config.CorsRegistry
import org.springframework.web.reactive.config.EnableWebFlux
import org.springframework.web.reactive.config.WebFluxConfigurer

@Configuration
@EnableWebFlux
class CORSConfig:WebFluxConfigurer {
    private companion object{
        const val FRONTEND_PORT = "3000"
        const val LOCALHOST = "http://localhost:"
        const val FRONTEND_ADDRESS = LOCALHOST+ FRONTEND_PORT
    }
    override fun addCorsMappings(registry: CorsRegistry) {
        registry.addMapping("/**").allowedOrigins(FRONTEND_ADDRESS).allowedMethods("*")
    }
}