package helpers


import java.math.BigInteger
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException


fun encrypt(message:String):String?{
    return try {
        val algorithm = MessageDigest.getInstance("SHA-256")
        val messageSecret = algorithm.digest(message.toByteArray())
        var hash = BigInteger(1, messageSecret).toString(16)

        while (hash.length < 32) {
            hash = "0$hash"
        }
        hash
    } catch (e: NoSuchAlgorithmException) {
        println("Exception: ${e.message}")
        null
    }

}