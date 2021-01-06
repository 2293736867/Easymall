package pers.wzr.easymall.entity.entity

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import pers.wzr.easymall.entity.util.ProductUtils
import pers.wzr.easymall.validator.ValidationGroup
import pers.wzr.easymall.validator.ValidationMessage
import java.util.*
import javax.validation.constraints.*

@Document(collection = "product")
open class Product(
    id: String = UUID.randomUUID().toString(),
    name: String = "",
    price: Double = 0.00,
    freight: Double = 0.00,
    category: String = "",
    num: Int = 0,
    rating: Double = 0.00,
    description: String = "",
) {
    @Id
    var id = id

    @NotBlank(message = ValidationMessage.NAME_IS_BLACK, groups = [ValidationGroup.ProductAdd::class,ValidationGroup.ProductUpdate::class])
    var name = name

    @Digits(
        integer = 15,
        fraction = 2,
        message = ValidationMessage.PRICE_FRACTION_INVALID,
        groups = [ValidationGroup.ProductAdd::class,ValidationGroup.ProductUpdate::class]
    )
    @DecimalMin(
        0.00.toString(),
        message = ValidationMessage.PRICE_NEGATIVE,
        groups = [ValidationGroup.ProductAdd::class,ValidationGroup.ProductUpdate::class]
    )
    var price = price

    @Digits(
        integer = 5,
        fraction = 2,
        message = ValidationMessage.FREIGHT_FRACTION_INVALID,
        groups = [ValidationGroup.ProductAdd::class,ValidationGroup.ProductUpdate::class]
    )
    @DecimalMin(
        0.00.toString(),
        message = ValidationMessage.FREIGHT_NEGATIVE,
        groups = [ValidationGroup.ProductAdd::class,ValidationGroup.ProductUpdate::class]
    )
    var freight = freight

    @NotBlank(message = ValidationMessage.CATEGORY_IS_BLACK, groups = [ValidationGroup.ProductAdd::class,ValidationGroup.ProductUpdate::class])
    var category = category

    @Min(0, message = ValidationMessage.NUM_NEGATIVE, groups = [ValidationGroup.ProductAdd::class,ValidationGroup.ProductUpdate::class])
    var num = num

    @Digits(
        integer = 2,
        fraction = 2,
        message = ValidationMessage.RATING_FRACTION_INVALID,
        groups = [ValidationGroup.ProductAdd::class,ValidationGroup.ProductUpdate::class]
    )
    @DecimalMin(
        0.00.toString(),
        message = ValidationMessage.RATING_NEGATIVE,
        groups = [ValidationGroup.ProductAdd::class,ValidationGroup.ProductUpdate::class]
    )
    @DecimalMax(
        10.00.toString(),
        message = ValidationMessage.RATING_TOO_LARGE,
        groups = [ValidationGroup.ProductAdd::class,ValidationGroup.ProductUpdate::class]
    )
    var rating = rating
    var description = description

    var coverImageBaseUrl = ""
    var thumbnailImageBaseUrl = ""
    var detailImageBaseUrl = ""
    var coverImagesSize = 0
    var detailImagesSize = 0
}