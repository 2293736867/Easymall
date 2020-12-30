package pers.wzr.easymall.entity.validation

import pers.wzr.easymall.validator.ValidationMessage
import javax.validation.constraints.Min
import javax.validation.constraints.NotBlank

class ProductAdd{
    @NotBlank(message = ValidationMessage.NAME_IS_BLACK)
    var name = ""
    @NotBlank(message = ValidationMessage.PRICE_IS_BLACK)
    var price = ""
    @NotBlank(message = ValidationMessage.CATEGORY_IS_BLACK)
    var category = ""
    @Min(0,message = ValidationMessage.NUM_NEGATIVE)
    var num = 0
    @Min(0,message = ValidationMessage.FREIGHT_NEGATIVE)
    var freight = 0
    var description = ""
}