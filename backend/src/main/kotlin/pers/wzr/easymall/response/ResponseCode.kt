package pers.wzr.easymall.response

class ResponseCode {
    companion object{
        const val USER_SIGN_IN_SUCCESS = "1000"
        const val USER_SIGN_IN_FAILED_USERNAME_OR_PASSWORD_ERROR = "1001"
        const val USER_SIGN_IN_FAILED_VERIFICATION_CODE_ERROR = "1002"
        const val USER_SIGN_UP_SUCCESS = "1003"
        const val USER_SIGN_UP_FAILED_VERIFICATION_CODE_ERROR = "1004"

        const val USER_USERNAME_NOT_EXISTS = "1005"
        const val USER_USERNAME_EXISTS = "1006"
        const val USER_DELETE_SUCCESS = "1007"
        const val USER_DELETE_FAILED_NOT_FOUND = "1008"
        const val USER_GET_ALL_SUCCESS = "1009"
        const val USER_GET_ONE_SUCCESS = "1010"
        const val USER_GET_ONE_FAILED_NOT_FOUND = "1011"
        const val USER_UPDATE_SUCCESS = "1012"
        const val USER_UPDATE_FAILED_NOT_FOUND = "1013"

        const val USER_NOT_SIGN_IN = "1014"
        const val USER_GET_NICKNAME_SUCCESS = "1015"
        const val USER_GET_NICKNAME_FAILED_NOT_FOUND = "1016"
        const val USER_GET_EMAIL_SUCCESS = "1017"
        const val USER_GET_EMAIL_FAILED_NOT_FOUND = "1018"

        const val PRODUCT_ADD_SUCCESS = "2000"
        const val PRODUCT_ADD_FAILED = "2001"
        const val PRODUCT_GET_ALL_SUCCESS = "2002"
        const val PRODUCT_GET_ALL_FAILED_EMPTY = "2003"
        const val PRODUCT_GET_ONE_SUCCESS = "2004"
        const val PRODUCT_GET_ONE_FAILED_NOT_FOUND = "2005"

        const val PRODUCT_DELETE_SUCCESS = "2006"
        const val PRODUCT_DELETE_FAILED_NOT_FOUND = "2007"
        const val PRODUCT_UPDATE_SUCCESS = "2008"
        const val PRODUCT_UPDATE_FAILED_NOT_EXISTS = "2009"

        const val PRODUCT_GET_BY_CATEGORY_SUCCESS = "2010"
        const val PRODUCT_GET_BY_CATEGORY_FAILED_NOT_FOUND = "2011"
        const val PRODUCT_GET_BY_CATEGORIES_SUCCESS = "2012"
        const val PRODUCT_GET_BY_CATEGORIES_FAILED_EMPTY = "2013"

        const val VALIDATION_USERNAME_IS_BLACK = "3000"
        const val VALIDATION_PASSWORD_IS_BLACK = "3001"
        const val VALIDATION_CODE_IS_BLACK = "3002"
        const val VALIDATION_CATEGORY_IS_BLACK = "3003"
        const val VALIDATION_NAME_IS_BLACK = "3004"
        const val VALIDATION_PRICE_IS_BLACK = "3005"
        const val VALIDATION_NUM_NEGATIVE = "3006"
        const val VALIDATION_FREIGHT_NEGATIVE = "3007"
        const val VALIDATION_NICKNAME_IS_BLACK = "3007"

        const val ERROR_GET_GATE_CODE = "100000"
        const val ERROR_VALIDATION_CODE = "100001"
    }
}