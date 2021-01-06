package pers.wzr.easymall.util

import org.springframework.web.reactive.function.server.ServerRequest

class Utils {
    companion object
    {
        fun getHeaderFromServerRequest(key:String,request: ServerRequest):String = request.headers().header(key)[0]

        fun removeFilePrefix(str:String) = str.substring(str.indexOf(":")+1)
    }
}