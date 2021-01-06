package pers.wzr.easymall.entity.util

import pers.wzr.easymall.entity.entity.Favourite

class FavouriteUtils {
    companion object{
        fun getFavoriteString(f: Favourite) = "id:${f.id},userId:${f.userId},productId:${f.productId}"
    }
}