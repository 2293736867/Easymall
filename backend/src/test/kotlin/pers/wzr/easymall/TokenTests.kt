package pers.wzr.easymall

import org.junit.jupiter.api.Test
import pers.wzr.easymall.util.JWTUtils

class TokenTests {
    @Test
    fun test()
    {
        val token1 = JWTUtils.generate("111")
        val token2 = JWTUtils.generate("111")
        println(token1)
        println(token2)
        println(token1 == token2)
    }
}