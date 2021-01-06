package pers.wzr.easymall.entity.entity

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import pers.wzr.easymall.validator.ValidationMessage
import java.util.*
import javax.validation.constraints.NotBlank

@Document(collection = "favourite")
class Favourite(
    @Id
    var id:String = UUID.randomUUID().toString(),
    @NotBlank(message = ValidationMessage.USER_ID_BLANK)
    var userId:String = "",
    @NotBlank(message = ValidationMessage.PRODUCT_ID_BLANK)
    var productId:String = ""
)