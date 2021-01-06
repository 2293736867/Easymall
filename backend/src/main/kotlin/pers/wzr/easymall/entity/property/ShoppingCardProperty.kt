package pers.wzr.easymall.entity.property

import pers.wzr.easymall.entity.entity.ShoppingCard

class ShoppingCardProperty {
    companion object{
        private val originProperties = arrayListOf<String>()
        private var properties = arrayListOf<String>()

        init{
            originProperties.add("_class")
            for(i in ShoppingCard::class.java.declaredFields)
            {
                originProperties.add(i.name)
            }
            properties.addAll(originProperties)
        }

        fun userId():String
        {
            val p = "userId"
            properties.remove(p)
            return p
        }

        fun productId():String
        {
            val p = "productId"
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