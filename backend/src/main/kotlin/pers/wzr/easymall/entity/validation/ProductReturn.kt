package pers.wzr.easymall.entity.validation

import pers.wzr.easymall.entity.entity.Product
import pers.wzr.easymall.validator.ValidationMessage
import javax.validation.constraints.Min
import javax.validation.constraints.NotBlank

class ProductReturn: Product() {
    var coverImageBaseUrl = ""
    var thumbnailImageBaseUrl = ""
    var detailImageBaseUrl = ""
    var coverImagesSize = 0
    var detailImagesSize = 0
}