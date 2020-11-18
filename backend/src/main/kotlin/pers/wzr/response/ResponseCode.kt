package pers.wzr.response

class ResponseCode {
    companion object
    {
        const val SIGN_IN_SUCCESS = "10000"
        const val SIGN_IN_FAILED_USERNAME_OR_PASSWORD_ERROR = "10001"
        const val SIGN_IN_FAILED_VERIFICATION_CODE_ERROR = "10002";

        const val SIGN_UP_SUCCESS = "20000"
        const val SIGN_UP_FAILED_VERIFICATION_CODE_ERROR = "20001"

        const val USERNAME_NOT_EXISTS = "30000"
        const val USERNAME_EXISTS = "30001"
    }
}