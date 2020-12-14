package pers.wzr.easymall.entity.validation

import pers.wzr.easymall.entity.entity.User
import pers.wzr.easymall.validator.ValidationGroup
import pers.wzr.easymall.validator.ValidationMessage
import javax.validation.constraints.NotBlank
import kotlin.reflect.KClass

class UserSignInAndSignUp {
    @NotBlank(message = ValidationMessage.USER_NAME_IS_BLANK,groups = [ValidationGroup.UserSignIn::class,ValidationGroup.UserSignUp::class])
    var username = ""
    @NotBlank(message = ValidationMessage.PASSWORD_IS_BLANK,groups = [ValidationGroup.UserSignIn::class,ValidationGroup.UserSignUp::class])
    var password = ""
    @NotBlank(message = ValidationMessage.NICKNAME_IS_BLANK,groups = [ValidationGroup.UserSignUp::class])
    var nickname = ""
    @NotBlank(message = ValidationMessage.EMAIL_IS_BLANK,groups = [ValidationGroup.UserSignUp::class])
    var email = ""
    @NotBlank(message = ValidationMessage.CODE_IS_BLANK,groups = [ValidationGroup.UserSignIn::class,ValidationGroup.UserSignUp::class])
    var code = ""
}