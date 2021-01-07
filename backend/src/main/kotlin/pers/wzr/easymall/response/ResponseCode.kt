package pers.wzr.easymall.response

class ResponseCode {
    companion object{
        //********************User********************

        //User Create
        const val USER_SIGN_UP_SUCCESS = "100000"
        const val USER_SIGN_UP_FAILED_VERIFICATION_CODE_ERROR = "100001"

        //User Retrieve
        const val USER_SIGN_IN_SUCCESS = "100100"
        const val USER_SIGN_IN_FAILED_USERNAME_OR_PASSWORD_ERROR = "100101"
        const val USER_SIGN_IN_FAILED_VERIFICATION_CODE_ERROR = "100102"

        const val USER_USERNAME_NOT_EXISTS = "100103"
        const val USER_USERNAME_EXISTS = "100104"

        const val USER_GET_ALL_SUCCESS = "100105"
        const val USER_GET_ALL_FAILED_EMPTY = "100106"

        //User Update
        const val USER_UPDATE_SUCCESS = "100200"
        const val USER_UPDATE_FAILED_NOT_FOUND = "100201"

        //User Delete
        const val USER_DELETE_SUCCESS = "100300"
        const val USER_DELETE_FAILED_NOT_FOUND = "100301"

        //User Other
        const val USER_NOT_SIGN_IN = "100400"

        //********************Product********************
        //Product Create
        const val PRODUCT_ADD_SUCCESS = "110000"
        const val PRODUCT_ADD_FAILED = "110001"

        //Product Retrieve
        const val PRODUCT_GET_ALL_SUCCESS = "110100"
        const val PRODUCT_GET_ALL_FAILED_EMPTY = "110101"

        const val PRODUCT_GET_ONE_SUCCESS = "110102"
        const val PRODUCT_GET_ONE_FAILED_NOT_FOUND = "110103"

        const val PRODUCT_GET_BY_CATEGORY_SUCCESS = "110104"
        const val PRODUCT_GET_BY_CATEGORY_FAILED_NOT_FOUND = "110105"

        const val PRODUCT_GET_BY_CATEGORIES_SUCCESS = "110106"
        const val PRODUCT_GET_BY_CATEGORIES_FAILED_EMPTY = "110107"

        const val PRODUCT_GET_DETAILS_SUCCESS = "110108"
        const val PRODUCT_GET_DETAILS_NOT_FOUND = "110109"

        //Product Update
        const val PRODUCT_UPDATE_SUCCESS = "110200"
        const val PRODUCT_UPDATE_FAILED_NOT_EXISTS = "110201"

        //Product Delete
        const val PRODUCT_DELETE_SUCCESS = "110300"
        const val PRODUCT_DELETE_FAILED_NOT_FOUND = "110301"

        //Product Other

        //ShoppingCard Create
        const val SHOPPING_CARD_ADD_SUCCESS = "120000"
        const val SHOPPING_CARD_ADD_FAILED = "120001"

        //ShoppingCard Retrieve
        const val SHOPPING_CARD_GET_BY_USER_ID_SUCCESS = "120100"
        const val SHOPPING_CARD_GET_BY_USER_ID_FAILED_EMPTY = "120101"

        //ShoppingCard Update
        const val SHOPPING_CARD_UPDATE_SUCCESS = "120200"
        const val SHOPPING_CARD_UPDATE_FAILED_NOT_EXIST = "120201"

        //ShoppingCard Delete
        const val SHOPPING_CARD_DELETE_SUCCESS = "120300"
        const val SHOPPING_CARD_DELETE_FAILED_NOT_FOUND = "120301"


        //********************Validation********************

        //Validation USER
        const val VALIDATION_USERNAME_IS_BLANK = "500000"
        const val VALIDATION_PASSWORD_IS_BLANK = "500001"
        const val VALIDATION_NICKNAME_IS_BLANK = "500002"
        const val VALIDATION_EMAIL_IS_BLANK = "500003"
        const val VALIDATION_CODE_IS_BLANK = "500004"

        //Validation Product
        const val VALIDATION_CATEGORY_IS_BLANK = "500100"
        const val VALIDATION_NAME_IS_BLANK = "500101"
        const val VALIDATION_PRICE_FRACTION_INVALID = "500102"
        const val VALIDATION_PRICE_NEGATIVE = "500103"
        const val VALIDATION_NUM_NEGATIVE = "500104"
        const val VALIDATION_FREIGHT_FRACTION_INVALID = "500105"
        const val VALIDATION_FREIGHT_NEGATIVE = "500106"
        const val VALIDATION_RATING_FRACTION_INVALID = "500107"
        const val VALIDATION_RATING_NEGATIVE = "500108"
        const val VALIDATION_RATING_TOO_LARGE = "500109"

        const val VALIDATION_USER_ID_IS_BLANK = "500200"
        const val VALIDATION_PRODUCT_ID_IS_BLANK = "500201"

        //Error Code
        const val ERROR_GET_GATE_CODE = "510000"
        const val ERROR_VALIDATION_CODE = "510001"
        const val ERROR_NULL_BODY = "510002"
    }
}