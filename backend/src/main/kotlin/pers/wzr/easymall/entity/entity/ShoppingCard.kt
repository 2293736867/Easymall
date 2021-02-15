package pers.wzr.easymall.entity.entity

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import pers.wzr.easymall.validator.ValidationMessage
import java.util.*
import javax.validation.constraints.Min
import javax.validation.constraints.NotBlank

@Document(collection = "shoppingCard")
class ShoppingCard(
    @Id
    var id:String = UUID.randomUUID().toString(),
    var userId:String = "",
    @NotBlank(message = ValidationMessage.PRODUCT_ID_BLANK)
    var productId:String = "",
    @Min(0,message = ValidationMessage.NUM_NEGATIVE)
    var num:Int = 0
)