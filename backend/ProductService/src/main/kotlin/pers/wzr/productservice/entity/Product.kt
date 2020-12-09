package pers.wzr.productservice.entity

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.util.*

@Document(collection = "product")
class Product{
    @Id
    var id = UUID.randomUUID().toString()
    var name = ""
    var price = 0.0
    var categoty = ""
    var pnum = 0
    var imageUrl = ""
    var description = ""

    override fun toString() = "id:$id,name:$name,price:$price\n" +
    "category:$categoty,pnum:$pnum,imageUrl:$imageUrl,description:$description"
}