package pers.wzr.easymall.entity.property

class ProductProperty {
    companion object{
        private val originProperties = arrayOf("id","_class","name","price","category","description","imageUrl","pnum")
        private var properties = arrayListOf<String>()

        init{
            properties.addAll(originProperties)
        }

        fun name():String
        {
            val p = "name"
            properties.remove(p)
            return p
        }

        fun price():String
        {
            val p = "price"
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