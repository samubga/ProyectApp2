package com.example.proyectapp.login

import android.util.Log
import java.security.SecureRandom
import java.security.spec.KeySpec
import javax.crypto.Mac
import javax.crypto.SecretKey
import javax.crypto.SecretKeyFactory
import javax.crypto.spec.PBEKeySpec
import javax.crypto.spec.SecretKeySpec

object PasswordUtils {
    //https://codersee.com/kotlin-pbkdf2-secure-password-hashing/

    private val ALGORITHM = "PBKDF2WithHmacSHA512"
    private val ITERATIONS = 120_000
    private val KEY_LENGTH = 256
    private val SECRET = "SomeRandomSecret"

     fun generateRandomSalt(): ByteArray {
        val random = SecureRandom()
        val salt = ByteArray(16)
        random.nextBytes(salt)
        return salt
    }
    fun generateHash(password: String, salt: ByteArray): String {

        val combinedSaltAndSecret = hashWithHmac(salt, SECRET.toByteArray())

        val factory: SecretKeyFactory = SecretKeyFactory.getInstance(ALGORITHM)
        val spec: KeySpec = PBEKeySpec(password.toCharArray(), combinedSaltAndSecret, ITERATIONS, KEY_LENGTH)
        val key: SecretKey = factory.generateSecret(spec)
        val hash: ByteArray = key.encoded
        Log.d("HashDebug", "Hash generado: ${hash.toHexString()}")

        return hash.toHexString()


    }

    private fun hashWithHmac(data: ByteArray, secret: ByteArray): ByteArray {
        val hmac = Mac.getInstance("HmacSHA256")
        val secretKey = SecretKeySpec(secret, "HmacSHA256")
        hmac.init(secretKey)
        return hmac.doFinal(data)
    }

    private fun ByteArray.toHexString(): String {
        val hexChars = "0123456789ABCDEF".toCharArray()
        val result = StringBuilder(this.size * 2)
        for (byte in this) {
            val upper = byte.toInt() ushr 4 and 0xF
            val lower = byte.toInt() and 0xF
            result.append(hexChars[upper])
            result.append(hexChars[lower])
        }
        return result.toString()
    }
}