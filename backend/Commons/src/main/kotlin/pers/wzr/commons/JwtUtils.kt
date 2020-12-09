package pers.wzr.commons

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import java.util.*

class JwtUtils {
    companion object{
        fun generateJWT(id:String, password: String) = JWT.create().withAudience(id).sign(Algorithm.HMAC512(password))
    }
}