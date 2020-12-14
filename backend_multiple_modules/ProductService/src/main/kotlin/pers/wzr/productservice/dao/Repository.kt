package pers.wzr.productservice.dao

import org.springframework.data.mongodb.repository.ReactiveMongoRepository
import org.springframework.data.repository.query.ReactiveQueryByExampleExecutor
import org.springframework.stereotype.Repository
import pers.wzr.productservice.entity.Product

@Repository
interface Repository :ReactiveMongoRepository<Product,String>,ReactiveQueryByExampleExecutor<Product>{
}