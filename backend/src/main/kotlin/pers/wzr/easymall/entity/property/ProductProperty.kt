package pers.wzr.easymall.entity.property

import pers.wzr.easymall.entity.entity.Product

class ProductProperty {
    companion object{
//        private val originProperties = arrayOf("id","_class","name","price","category","description","num")
        private val originProperties = arrayListOf<String>()
        private var properties = arrayListOf<String>()

        init{
            originProperties.add("_class")
            for(i in Product::class.java.declaredFields)
            {
                originProperties.add(i.name)
            }
            properties.addAll(originProperties)
        }

        fun name():String
        {
            val p = "name"
            properties.remove(p)
            return p
        }

        fun category():String
        {
            val p = "category"
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