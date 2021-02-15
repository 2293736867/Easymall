package pers.wzr.easymall.entity.util

import org.springframework.web.reactive.function.server.ServerRequest
import pers.wzr.easymall.entity.entity.Product
import pers.wzr.easymall.util.Utils
import java.nio.file.Files
import java.nio.file.Path

class ProductUtils {
    companion object {
        private const val BASE_IMAGE_URL = "http://localhost:8080/product/get/image/"

        fun productResultFromProduct(p: Product):Product {
            p.coverImageBaseUrl = BASE_IMAGE_URL + p.id + "/cover/"
            p.thumbnailImageBaseUrl = BASE_IMAGE_URL + p.id + "/thumbnail/"
            p.detailImageBaseUrl = BASE_IMAGE_URL + p.id + "/detail/"
            return p
        }

        fun getProductTokenFromServerRequest(request: ServerRequest) =
            Utils.getHeaderFromServerRequest("productToken", request)

        fun getCoverImagesNum(id: String): Int {
            return getImagesNum(id, "cover")
        }

        fun getDetailImagesNum(id: String): Int {
            return getImagesNum(id, "detail")
        }

        fun getImagesNum(id: String, imagePath: String): Int {
            val path = Path.of(Utils::class.java.classLoader.getResource("img/$id/$imagePath")?.toURI()) ?: return 0
            var num = 0
            try {
                val f = Files.newDirectoryStream(path, "*.jpg")
                f.forEach { _ -> ++num }
            } catch (e: Exception) {
                e.printStackTrace()
            }
            return num
        }

        fun printProduct(p: Product) {
            Product::class.java.declaredFields.forEach {
                it.isAccessible = true
                print(it.name + ":" + it.get(p) + ",")
            }
            println()
        }
    }
}