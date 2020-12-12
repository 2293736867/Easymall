package pers.wzr.easymall.util

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm

class JwtUtils {
    companion object{
        fun generateJWT(id:String, password: String): String = JWT.create().withAudience(id).sign(Algorithm.HMAC512(password))
    }
}