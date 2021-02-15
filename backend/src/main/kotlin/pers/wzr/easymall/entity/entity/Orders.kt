package pers.wzr.easymall.entity.entity

import org.springframework.data.mongodb.core.mapping.Document
import java.util.*

@Document(collection = "orders")
class Orders(
    var id: String = UUID.randomUUID().toString(),
    var userId: String = "",
    var productId: String = "",
    var state:String = "",
    var createTime:Long = 0L,
    var paidTime:Long = 0L
)