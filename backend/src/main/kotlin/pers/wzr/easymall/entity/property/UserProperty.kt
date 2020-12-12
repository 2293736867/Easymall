package pers.wzr.easymall.entity.property

class UserProperty {
    companion object{
        private val originProperties = arrayOf("id","_class","username","password","nickname","email")
        private var properties = arrayListOf<String>()

        init{
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