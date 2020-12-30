package pers.wzr.easymall.cache

import pers.wzr.easymall.entity.entity.Product
import pers.wzr.easymall.entity.validation.ProductReturn
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

class ProductCache {
    var monoProduct = Mono.empty<ProductReturn>()
    var fluxProduct = Flux.empty<ProductReturn>()
}