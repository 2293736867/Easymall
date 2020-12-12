package pers.wzr.userservice.dao

import org.springframework.data.mongodb.repository.ReactiveMongoRepository
import org.springframework.data.repository.query.ReactiveQueryByExampleExecutor
import org.springframework.stereotype.Repository
import pers.wzr.userservice.entity.User

@Repository
interface Repository :ReactiveMongoRepository<User,String>,ReactiveQueryByExampleExecutor<User>