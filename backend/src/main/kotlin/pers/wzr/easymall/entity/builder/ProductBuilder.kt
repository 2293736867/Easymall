package pers.wzr.easymall.entity.builder

import pers.wzr.easymall.entity.entity.Product

class ProductBuilder {
    private var product = Product()
    fun id(id:String): ProductBuilder
    {
        product.id = id
        return this
    }

    fun name(name:String): ProductBuilder
    {
        product.name = name
        return this
    }

    fun price(price:String): ProductBuilder
    {
        product.price = price
        return this
    }

    fun category(category: String): ProductBuilder
    {
        product.category = category
        return this
    }

    fun pnm(pnum:Int): ProductBuilder
    {
        product.num = pnum
        return this
    }

    fun description(des:String):ProductBuilder
    {
        product.description = des
        return this
    }

    fun build() = product
}