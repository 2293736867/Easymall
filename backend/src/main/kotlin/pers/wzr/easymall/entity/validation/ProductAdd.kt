package pers.wzr.easymall.entity.validation

import pers.wzr.easymall.validator.ValidationMessage
import javax.validation.constraints.Min
import javax.validation.constraints.NotBlank

class ProductAdd{
    @NotBlank(message = ValidationMessage.NAME_IS_BLACK)
    var name = ""
    @Min(1)
    var price = 0.0
    @NotBlank(message = ValidationMessage.CATEGORY_IS_BLACK)
    var categoty = ""
    @Min(1)
    var pnum = 0
    var description = ""
}