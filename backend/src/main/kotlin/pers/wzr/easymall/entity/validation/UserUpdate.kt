package pers.wzr.easymall.entity.validation

import pers.wzr.easymall.entity.entity.User
import pers.wzr.easymall.validator.ValidationGroup
import pers.wzr.easymall.validator.ValidationMessage
import javax.validation.constraints.NotBlank
import kotlin.reflect.KClass

class UserUpdate {
    @NotBlank(message = ValidationMessage.PASSWORD_IS_BLANK)
    var password = ""
    @NotBlank(message = ValidationMessage.NICKNAME_IS_BLANK)
    var nickname = ""
    @NotBlank(message = ValidationMessage.EMAIL_IS_BLANK)
    var email = ""

    override fun toString() = "password:$password\tnickname:$nickname\temail:$email"
}