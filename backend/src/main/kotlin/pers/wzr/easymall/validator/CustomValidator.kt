package pers.wzr.easymall.validator

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.ServerResponse
import pers.wzr.easymall.response.JSONResponse
import pers.wzr.easymall.response.ResponseCode
import reactor.core.publisher.Mono
import javax.validation.Validator

@Component
class CustomValidator {
    @Autowired
    lateinit var validator: Validator
    private var response = Mono.empty<ServerResponse>()

    fun <T> hasErrors(obj: T, vararg group: Class<*>): Boolean {
        val e = if (group.isEmpty()) validator.validate(obj) else validator.validate(obj, group[0])
        e.forEach{
            response = when(it.message) {
                ValidationMessage.USER_NAME_IS_BLANK -> JSONResponse.code(ResponseCode.VALIDATION_USERNAME_IS_BLANK)
                ValidationMessage.PASSWORD_IS_BLANK -> JSONResponse.code(ResponseCode.VALIDATION_PASSWORD_IS_BLANK)
                ValidationMessage.CODE_IS_BLANK -> JSONResponse.code(ResponseCode.VALIDATION_CODE_IS_BLANK)
                ValidationMessage.NICKNAME_IS_BLANK -> JSONResponse.code(ResponseCode.VALIDATION_NICKNAME_IS_BLANK)
                ValidationMessage.EMAIL_IS_BLANK -> JSONResponse.code(ResponseCode.VALIDATION_EMAIL_IS_BLANK)

                ValidationMessage.NAME_IS_BLACK -> JSONResponse.code(ResponseCode.VALIDATION_NAME_IS_BLANK)
                ValidationMessage.CATEGORY_IS_BLACK -> JSONResponse.code(ResponseCode.VALIDATION_CATEGORY_IS_BLANK)
                ValidationMessage.PRICE_FRACTION_INVALID -> JSONResponse.code(ResponseCode.VALIDATION_PRICE_FRACTION_INVALID)
                ValidationMessage.PRICE_NEGATIVE -> JSONResponse.code(ResponseCode.VALIDATION_PRICE_NEGATIVE)
                ValidationMessage.NUM_NEGATIVE ->  JSONResponse.code(ResponseCode.VALIDATION_NUM_NEGATIVE)
                ValidationMessage.FREIGHT_FRACTION_INVALID -> JSONResponse.code(ResponseCode.VALIDATION_FREIGHT_FRACTION_INVALID)
                ValidationMessage.FREIGHT_NEGATIVE -> JSONResponse.code(ResponseCode.VALIDATION_FREIGHT_NEGATIVE)
                ValidationMessage.RATING_FRACTION_INVALID -> JSONResponse.code(ResponseCode.VALIDATION_RATING_FRACTION_INVALID)
                ValidationMessage.RATING_NEGATIVE -> JSONResponse.code(ResponseCode.VALIDATION_RATING_NEGATIVE)
                ValidationMessage.RATING_TOO_LARGE -> JSONResponse.code(ResponseCode.VALIDATION_RATING_TOO_LARGE)

                ValidationMessage.USER_ID_BLANK -> JSONResponse.code(ResponseCode.VALIDATION_USER_ID_IS_BLANK)
                ValidationMessage.PRODUCT_ID_BLANK -> JSONResponse.code(ResponseCode.VALIDATION_PRODUCT_ID_IS_BLANK)

                else -> JSONResponse.code(ResponseCode.ERROR_VALIDATION_CODE)
            }
        }
        return e.isNotEmpty()
    }

    fun errors() = response
}