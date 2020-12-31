package pers.wzr.easymall.validator

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.ServerResponse
import pers.wzr.easymall.response.CommonResponse
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
                ValidationMessage.USER_NAME_IS_BLANK -> CommonResponse.code(ResponseCode.VALIDATION_USERNAME_IS_BLACK)
                ValidationMessage.PASSWORD_IS_BLANK -> CommonResponse.code(ResponseCode.VALIDATION_PASSWORD_IS_BLACK)
                ValidationMessage.CODE_IS_BLANK -> CommonResponse.code(ResponseCode.VALIDATION_CODE_IS_BLACK)
                ValidationMessage.NAME_IS_BLACK -> CommonResponse.code(ResponseCode.VALIDATION_NAME_IS_BLACK)
                ValidationMessage.CATEGORY_IS_BLACK -> CommonResponse.code(ResponseCode.VALIDATION_CATEGORY_IS_BLACK)
                ValidationMessage.PRICE_IS_BLACK -> CommonResponse.code(ResponseCode.VALIDATION_PRICE_IS_BLACK)
                ValidationMessage.NUM_NEGATIVE ->  CommonResponse.code(ResponseCode.VALIDATION_NUM_NEGATIVE)
                ValidationMessage.FREIGHT_NEGATIVE -> CommonResponse.code(ResponseCode.VALIDATION_FREIGHT_NEGATIVE)
                ValidationMessage.NICKNAME_IS_BLANK -> CommonResponse.code(ResponseCode.VALIDATION_NICKNAME_IS_BLACK)
                else -> CommonResponse.code(ResponseCode.ERROR_VALIDATION_CODE)
            }
        }
        return e.isNotEmpty()
    }

    fun errors() = response
}