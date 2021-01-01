package pers.wzr.easymall.util

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import org.bouncycastle.util.io.pem.PemReader
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.ServerRequest
import org.yaml.snakeyaml.Yaml
import java.io.File
import java.io.FileInputStream
import java.io.FileNotFoundException
import java.io.FileReader
import java.security.KeyFactory
import java.security.interfaces.ECPrivateKey
import java.security.interfaces.ECPublicKey
import java.security.spec.PKCS8EncodedKeySpec
import java.security.spec.X509EncodedKeySpec

@Component
class JWTUtils {
    companion object {
        private var key = ""
        private val PUBLIC_KEY_PATH = JWTUtils::class.java.classLoader.getResource("ecdsa/public.pem")?.toString()
        private val PRIVATE_KEY_PATH = JWTUtils::class.java.classLoader.getResource("ecdsa/newprivate.pem")?.toString()
        private val algorithm = getTokenAlgorithm()
        private val builder = JWT.create().withIssuer("issuer")

        init {
            val str = JWTUtils::class.java.classLoader.getResource("application.yml")?.toString()
            key = if (str != null) {
                val result = Yaml().load<LinkedHashMap<String, String>>(FileInputStream(File(Utils.removeFilePrefix(str))))
                val jwt = result["jwt"] as LinkedHashMap<String, String>
                jwt["key"] as String
            } else
                "default key"
        }

        fun generate(id: String): String {
            return builder.withAudience(id).sign(algorithm)
        }

        fun getIdFromServerRequest(request: ServerRequest): String {
            val token = Utils.getUserTokenFromServerRequest(request)
            return JWT.decode(token).audience[0]
        }

        private fun parsePEMFile(pemFile: File): ByteArray {
            if (!pemFile.isFile || !pemFile.exists())
                throw FileNotFoundException(String.format("The file '%s' doesn't exist.", pemFile.absolutePath))
            val reader = PemReader(FileReader(pemFile))
            val content = reader.readPemObject().content
            reader.close()
            return content
        }

        private fun getTokenAlgorithm(): Algorithm {
            if (PUBLIC_KEY_PATH == null || PRIVATE_KEY_PATH == null)
                return Algorithm.HMAC512(key)
            return Algorithm.ECDSA512(
                KeyFactory.getInstance("EC").generatePublic(
                    X509EncodedKeySpec(
                        parsePEMFile(File(Utils.removeFilePrefix(PUBLIC_KEY_PATH)))
                    )
                ) as ECPublicKey,
                KeyFactory.getInstance("EC").generatePrivate(
                    PKCS8EncodedKeySpec(
                        parsePEMFile(File(Utils.removeFilePrefix(PRIVATE_KEY_PATH)))
                    )
                ) as ECPrivateKey
            )
        }
    }
}