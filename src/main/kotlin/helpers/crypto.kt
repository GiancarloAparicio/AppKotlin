package helpers


import java.math.BigInteger
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException


fun encrypt(secret:String):String?{
    return try {
        val md = MessageDigest.getInstance("SHA-256")
        val messageDigest = md.digest(secret.toByteArray())
        val num = BigInteger(1, messageDigest)
        var hash = num.toString(16)
        while (hash.length < 32) {
            hash = "0$hash"
        }
        hash
    } catch (ex: NoSuchAlgorithmException) {
        println("Exception: ${ex.message}")
        null
    }

}