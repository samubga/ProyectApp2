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


    /*
        -Un "salt" (sal) es una cadena de bytes aleatoria que se
         agrega a la contraseña antes de aplicarle el proceso de hashing

        -Aumenta la seguridad del hash al hacer que cada contraseña
         hasheada sea única, incluso si las contraseñas originales son iguales.
     */
     fun generateRandomSalt(): ByteArray {
        val random = SecureRandom()
        val salt = ByteArray(16)
        random.nextBytes(salt)
        return salt
    }



    /*
        -Este método genera un hash de la contraseña utilizando el algoritmo PBKDF2 con HMAC-SHA512.
        -Combina el salt generado con un secreto adicional y luego utiliza este valor combinado como input para
        la generación del hash.
        -Utiliza la función hashWithHmac para generar el valor combinado.
        -Utiliza SecretKeyFactory y PBEKeySpec para generar una clave secreta basada en la contraseña y el salt.
        -Devuelve el hash generado como una cadena hexadecimal.

     */
    fun generateHash(password: String, salt: ByteArray): String {

        val combinedSaltAndSecret = hashWithHmac(salt, SECRET.toByteArray())

        val factory: SecretKeyFactory = SecretKeyFactory.getInstance(ALGORITHM)
        val spec: KeySpec = PBEKeySpec(password.toCharArray(), combinedSaltAndSecret, ITERATIONS, KEY_LENGTH)
        val key: SecretKey = factory.generateSecret(spec)
        val hash: ByteArray = key.encoded
        Log.d("HashDebug", "Hash generado: ${hash.toHexString()}")

        return hash.toHexString()
    }



    /*
        -Este método utiliza HMAC (Hash-based Message Authentication Code) con SHA-256 para combinar los
        datos con un secreto y generar un valor hash.

        -Utiliza la clase Mac y SecretKeySpec para crear un objeto HmacSHA256 y una clave secreta basada
        en el secreto proporcionado.

        -Devuelve el valor hash generado como un array de bytes.
     */
    private fun hashWithHmac(data: ByteArray, secret: ByteArray): ByteArray {
        val hmac = Mac.getInstance("HmacSHA256")
        val secretKey = SecretKeySpec(secret, "HmacSHA256")
        hmac.init(secretKey)
        return hmac.doFinal(data)
    }




    /*

    -Este método convierte un array de bytes en una cadena hexadecimal.
    -Itera sobre cada byte en el array y lo convierte en dos caracteres hexadecimales correspondientes.
    -Devuelve la representación hexadecimal de los bytes como una cadena de texto.

     */
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