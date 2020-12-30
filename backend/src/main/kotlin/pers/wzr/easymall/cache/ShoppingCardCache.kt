package pers.wzr.easymall.cache

import pers.wzr.easymall.entity.entity.ShoppingCard
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

class ShoppingCardCache {
    var monoShoppingCard = Mono.empty<ShoppingCard>()
    var fluxShoppingCard = Flux.empty<ShoppingCard>()
}