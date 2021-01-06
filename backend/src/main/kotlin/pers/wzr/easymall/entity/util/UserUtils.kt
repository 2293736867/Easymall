package pers.wzr.easymall.entity.util

import org.springframework.web.reactive.function.server.ServerRequest
import pers.wzr.easymall.entity.entity.User
import pers.wzr.easymall.util.Utils

class UserUtils {
    companion object{
        fun getUserTokenFromServerRequest(request: ServerRequest) = Utils.getHeaderFromServerRequest("userToken",request)

        fun printUser(u: User){
            User::class.java.declaredFields.forEach{
                it.isAccessible = true
                print(it.name+":"+it.get(u)+",")
            }
            println()
        }
    }
}