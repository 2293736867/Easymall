package pers.wzr.gateway.constant

class URI {
    companion object{
        private const val PROTOCOL = "http"
        private const val SEPARATOR = "://"
        private const val HOST = "localhost"
        private const val PREFIX = "$PROTOCOL$SEPARATOR$HOST:"
        const val PRODUCT = PREFIX + "8081"
        const val USER = PREFIX + "8082"
    }
}