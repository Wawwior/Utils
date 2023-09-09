package me.wawwior.utils.hashing

import java.nio.charset.Charset

interface Hash {

    fun hash(string: String): ByteArray {
        return hash(string, Charsets.UTF_8)
    }

    fun hash(string: String, charset: Charset): ByteArray {
        return hash(string.toByteArray(charset))
    }

    fun hash(bytes: ByteArray): ByteArray

    fun compare(bytes: ByteArray, hash: ByteArray): Boolean

    fun hashLength(): Int

}