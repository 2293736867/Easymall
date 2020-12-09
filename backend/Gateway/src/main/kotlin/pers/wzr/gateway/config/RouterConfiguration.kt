package pers.wzr.gateway.config

import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder
import org.springframework.cloud.gateway.route.builder.filters
import org.springframework.cloud.gateway.route.builder.routes
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.function.server.ServerResponse
import pers.wzr.gateway.constant.Path
import pers.wzr.gateway.constant.URI
import reactor.core.publisher.Mono

@Configuration
class RouterConfiguration {
    companion object ID{
        private const val id = "host_route"
    }

    @Bean
    fun routerConfig(builder:RouteLocatorBuilder) = builder.routes{
        route(id){
            path(Path.PRODUCT)
            filters {
                stripPrefix(1)
            }
            uri(URI.PRODUCT)
        }
        route(id){
            path(Path.USER)
            filters {
                stripPrefix(1)
            }
            uri(URI.USER)
        }
    }
}