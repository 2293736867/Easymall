package pers.wzr.verificationcode.utils

import cn.hutool.captcha.ShearCaptcha

class Utils
{
    companion object {
        private var code = ShearCaptcha(150,25)

        fun getBase64():String {
            code = ShearCaptcha(150,25,4,0)
            return code.imageBase64
        }

        fun verify(c:String) = code.verify(c)
    }
}