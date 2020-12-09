package pers.wzr.entity

//import kotlinx.serialization.Serializable
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.util.*

@Document(collection = "user")
//@Serializable
class User{
    @Id
    var id = UUID.randomUUID().toString()
    var username = ""
    var password = ""
    var nickname = ""
    var email = ""

    override fun toString() = "id:$id,username:$username,password:$password,nickname:$nickname,email:$email"
}