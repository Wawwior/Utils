package me.wawwior.utils.hashing.hashes

import me.wawwior.utils.hashing.Hash
import java.security.MessageDigest

class DefaultHash(private val messageDigest: MessageDigest) : Hash {

    override fun hashLength() = messageDigest.digestLength

    override fun hash(bytes: ByteArray): ByteArray = messageDigest.digest(bytes)

    override fun compare(bytes: ByteArray, hash: ByteArray): Boolean {
        if (hash.size != hashLength())
            return false
        return hash(bytes).contentEquals(hash)
    }
}