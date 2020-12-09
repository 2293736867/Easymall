package pers.wzr.dao

import org.springframework.data.mongodb.repository.ReactiveMongoRepository
import org.springframework.data.repository.query.ReactiveQueryByExampleExecutor
import org.springframework.stereotype.Repository
import pers.wzr.entity.User

@Repository
interface UserRepository:ReactiveMongoRepository<User,String>,ReactiveQueryByExampleExecutor<User>
