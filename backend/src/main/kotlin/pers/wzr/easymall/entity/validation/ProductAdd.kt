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
    @Min(1)
    var num = 0
    var description = ""
}