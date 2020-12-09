package pers.wzr.dao

import org.springframework.data.mongodb.repository.ReactiveMongoRepository
import org.springframework.data.repository.query.ReactiveQueryByExampleExecutor
import org.springframework.stereotype.Repository
import pers.wzr.entity.Product

@Repository
interface ProductRepository:ReactiveMongoRepository<Product,String>,ReactiveQueryByExampleExecutor<Product>
