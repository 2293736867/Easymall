package pers.wzr.easymall.entity.validation

import pers.wzr.easymall.validator.ValidationMessage
import javax.validation.constraints.NotBlank

class UserSignInAndSignUp {
    @NotBlank(message = ValidationMessage.USER_NAME_IS_BLANK)
    var username = ""
    @NotBlank(message = ValidationMessage.PASSWORD_IS_BLANK)
    var password = ""
    @NotBlank(message = ValidationMessage.CODE_IS_BLANK)
    var code = ""
}