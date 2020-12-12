package pers.wzr.easymall.cache

import pers.wzr.easymall.entity.entity.Product
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

class ProductQueryCache {
    var monoProduct = Mono.empty<Product>()
    var fluxProduct = Flux.empty<Product>()
}