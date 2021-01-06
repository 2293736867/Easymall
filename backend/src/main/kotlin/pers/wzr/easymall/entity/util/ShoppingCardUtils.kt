package pers.wzr.easymall.entity.util

import pers.wzr.easymall.entity.entity.ShoppingCard

class ShoppingCardUtils {
    companion object{
        fun getShoppingCardString(s: ShoppingCard) = "id:${s.id},userId:${s.userId},productId:${s.productId},num:${s.num}"
    }
}