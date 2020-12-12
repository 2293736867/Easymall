package pers.wzr.gateway.constant

class Path {
    companion object{
        private const val PREFIX = "/"
        private const val SUFFIX = "/**"
        val PRODUCT = path("product")
        val USER = path("user")

        private fun path(str:String) = PREFIX + str + SUFFIX
    }
}