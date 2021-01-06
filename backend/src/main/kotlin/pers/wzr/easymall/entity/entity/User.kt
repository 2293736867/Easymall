package pers.wzr.easymall.entity.entity

import com.fasterxml.jackson.annotation.JsonProperty
import org.bson.codecs.pojo.annotations.BsonIgnore
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import pers.wzr.easymall.validator.ValidationGroup
import pers.wzr.easymall.validator.ValidationMessage
import java.util.*
import javax.validation.constraints.NotBlank

@Document(collection = "user")
class User(
    id: String = UUID.randomUUID().toString(),
    username: String = "",
    password: String = "",
    nickname: String = "",
    email: String = "",
    code: String = ""
) {
    @Id
    var id = id

    @NotBlank(
        message = ValidationMessage.USER_NAME_IS_BLANK,
        groups = [ValidationGroup.UserSignIn::class, ValidationGroup.UserSignUp::class,ValidationGroup.UserUpdate::class]
    )
    var username = username

    @NotBlank(
        message = ValidationMessage.PASSWORD_IS_BLANK,
        groups = [ValidationGroup.UserSignIn::class, ValidationGroup.UserSignUp::class,ValidationGroup.UserUpdate::class]
    )
    var password = password

    @NotBlank(
        message = ValidationMessage.NICKNAME_IS_BLANK,
        groups = [ValidationGroup.UserSignUp::class,ValidationGroup.UserUpdate::class]
    )
    var nickname = nickname

    @NotBlank(
        message = ValidationMessage.EMAIL_IS_BLANK,
        groups = [ValidationGroup.UserSignUp::class,ValidationGroup.UserUpdate::class]
    )
    var email = email

    @BsonIgnore
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @NotBlank(
        message = ValidationMessage.CODE_IS_BLANK,
        groups = [ValidationGroup.UserSignIn::class, ValidationGroup.UserSignUp::class]
    )
    var code = code
}