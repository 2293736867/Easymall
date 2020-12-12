package pers.wzr.easymall.cache

import pers.wzr.easymall.entity.entity.User
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

class UserQueryCache {
    var monoUser = Mono.empty<User>()
    var fluxUser = Flux.empty<User>()
}