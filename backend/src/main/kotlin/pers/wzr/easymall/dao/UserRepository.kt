package pers.wzr.easymall.dao

import org.springframework.data.mongodb.repository.ReactiveMongoRepository
import org.springframework.data.repository.query.ReactiveQueryByExampleExecutor
import org.springframework.stereotype.Repository
import pers.wzr.easymall.entity.entity.User

@Repository
interface UserRepository:ReactiveMongoRepository<User,String>,ReactiveQueryByExampleExecutor<User>