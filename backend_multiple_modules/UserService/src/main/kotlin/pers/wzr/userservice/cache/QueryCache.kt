package pers.wzr.userservice.cache

import pers.wzr.userservice.entity.User
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

class QueryCache {
    var monoUser = Mono.empty<User>()
    var fluxUser = Flux.empty<User>()
}