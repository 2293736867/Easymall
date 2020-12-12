package pers.wzr.productservice.cache

import pers.wzr.productservice.entity.Product
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

class QueryCache {
    var monoProduct = Mono.empty<Product>()
    var fluxProduct = Flux.empty<Product>()
}