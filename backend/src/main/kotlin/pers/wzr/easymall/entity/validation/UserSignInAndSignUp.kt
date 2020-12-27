package pers.wzr.easymall.entity.validation

import pers.wzr.easymall.validator.ValidationGroup
import pers.wzr.easymall.validator.ValidationMessage
import javax.validation.constraints.NotBlank

class UserSignInAndSignUp {
    @NotBlank(message = ValidationMessage.USER_NAME_IS_BLANK,groups = [ValidationGroup.UserSignIn::class,ValidationGroup.UserSignUp::class])
    var username = ""
    @NotBlank(message = ValidationMessage.PASSWORD_IS_BLANK,groups = [ValidationGroup.UserSignIn::class,ValidationGroup.UserSignUp::class])
    var password = ""
    @NotBlank(message = ValidationMessage.EMAIL_IS_BLANK,groups = [ValidationGroup.UserSignUp::class])
    var email = ""
    @NotBlank(message = ValidationMessage.CODE_IS_BLANK,groups = [ValidationGroup.UserSignIn::class,ValidationGroup.UserSignUp::class])
    var code = ""

    override fun toString() = "username:$username\npassword:$password\nemail:$email\ncode:$code"
}