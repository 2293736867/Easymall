package pers.wzr.easymall.util

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm

class JWT {
    companion object{
        fun generate(id:String, password: String): String = JWT.create().withAudience(id).sign(Algorithm.HMAC512(password))
    }
}