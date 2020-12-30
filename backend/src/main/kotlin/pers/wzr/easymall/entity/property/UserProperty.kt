package pers.wzr.easymall.entity.property

import pers.wzr.easymall.entity.entity.User

class UserProperty {
    companion object{
//        private val originProperties = arrayOf("id","_class","username","password","nickname","email")
        private val originProperties = arrayListOf<String>()
        private var properties = arrayListOf<String>()

        init{
            originProperties.add("_class")
            for(i in User::class.java.declaredFields)
            {
                originProperties.add(i.name)
            }
            properties.addAll(originProperties)
        }

        fun username():String
        {
            val p = "username"
            properties.remove(p)
            return p
        }

        fun password():String
        {
            val p = "password"
            properties.remove(p)
            return p
        }

        fun other():Array<String>{
            var s = arrayOf<String>()
            s = properties.toArray(s)
            properties.clear()
            properties.addAll(originProperties)
            return s
        }
    }
}