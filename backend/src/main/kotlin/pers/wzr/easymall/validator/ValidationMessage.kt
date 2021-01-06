package pers.wzr.easymall.validator

class ValidationMessage {
    companion object{
        const val USER_NAME_IS_BLANK = "100"
        const val PASSWORD_IS_BLANK = "101"
        const val CODE_IS_BLANK = "102"
        const val EMAIL_IS_BLANK = "103"
        const val NICKNAME_IS_BLANK = "104"

        const val NAME_IS_BLACK = "200"
        const val CATEGORY_IS_BLACK = "201"
        const val PRICE_FRACTION_INVALID = "202"
        const val PRICE_NEGATIVE = "203"
        const val NUM_NEGATIVE = "204"
        const val FREIGHT_FRACTION_INVALID = "205"
        const val FREIGHT_NEGATIVE = "206"
        const val RATING_FRACTION_INVALID = "207"
        const val RATING_NEGATIVE = "208"
        const val RATING_TOO_LARGE = "209"

        const val USER_ID_BLANK = "300"
        const val PRODUCT_ID_BLANK = "301"
    }
}