package pers.wzr.easymall.util

import pers.wzr.easymall.entity.entity.Product
import pers.wzr.easymall.entity.validation.ProductReturn
import java.nio.file.Files
import java.nio.file.Path

class Utils {
    companion object
    {
        private const val BASE_IMAGE_URL = "http://localhost:8080/product/get/image/"

        private fun getCoverImagesNum(id:String):Int{
            return getImagesNum(id,"cover")
        }

        private fun getDetailImagesNum(id:String):Int
        {
            return getImagesNum(id,"detail")
        }

        private fun getImagesNum(id:String,imagePath:String):Int
        {
            val path = Path.of(Utils::class.java.classLoader.getResource("img/$id/$imagePath")?.toURI()) ?: return 0
            var num = 0
            try{
                val f = Files.newDirectoryStream(path,"*.jpg")
                f.forEach{ _ -> ++num}
            }
            catch (e:Exception)
            {
                e.printStackTrace()
            }
            return num
        }

        fun productResultFromProduct(p: Product):ProductReturn{
            val result = ProductReturn()
            result.id = p.id
            result.num = p.num
            result.description = p.description
            result.name = p.name
            result.category = p.category
            result.price = p.price
            result.coverImageBaseUrl= BASE_IMAGE_URL+p.id+"/cover/"
            result.thumbnailImageBaseUrl= BASE_IMAGE_URL+p.id+"/thumbnail/"
            result.detailImageBaseUrl = BASE_IMAGE_URL+p.id+"/detail/"
            result.coverImagesSize = getCoverImagesNum(p.id)
            result.detailImagesSize = getDetailImagesNum(p.id)
            return result
        }

        fun productCopy(old:Product,new:Product){
            old.category = new.category
            old.description = new.description
            old.freight = new.freight
            old.num = new.num
            old.price = new.price
        }
    }
}