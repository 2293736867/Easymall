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

    fun price(price:Double): ProductBuilder
    {
        product.price = price
        return this
    }

    fun category(category: String): ProductBuilder
    {
        product.categoty = category
        return this
    }

    fun pnm(pnum:Int): ProductBuilder
    {
        product.pnum = pnum
        return this
    }

    fun build() = product
}