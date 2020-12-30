package pers.wzr.easymall.entity.entity

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.util.*

@Document(collection = "product")
open class Product{
    @Id
    var id = UUID.randomUUID().toString()
    var name = ""
    var price = ""
    var freight = 0
    var category = ""
    var num = 0
    var description = ""
}