package me.wawwior.utils.hashing.hashes

import me.wawwior.utils.hashing.Hash
import me.wawwior.utils.hashing.Salt
import java.security.MessageDigest

class DefaultSaltedHash(private val messageDigest: MessageDigest, private val salt: Salt) : Hash {
    override fun hashLength() = messageDigest.digestLength + salt.getLength()

    override fun hash(bytes: ByteArray): ByteArray = if (salt.getLength() == 0) messageDigest.digest(bytes) else hash(bytes, salt.getSalt())

    private fun hash(bytes: ByteArray, salt: ByteArray): ByteArray {
        val saltedBytes = ByteArray(bytes.size + salt.size)
        System.arraycopy(bytes, 0, saltedBytes, 0, bytes.size)
        System.arraycopy(salt, 0, saltedBytes, bytes.size, salt.size)
        val saltedHash = messageDigest.digest(saltedBytes)
        val combinedHash = ByteArray(saltedHash.size + salt.size)
        System.arraycopy(salt, 0, combinedHash, 0, salt.size)
        System.arraycopy(saltedHash, 0, combinedHash, salt.size, saltedHash.size)
        return combinedHash
    }

    override fun compare(bytes: ByteArray, hash: ByteArray): Boolean {
        if (hash.size != hashLength())
            return false
        if (salt.getLength() == 0)
            return hash(bytes).contentEquals(hash)
        val salt = ByteArray(this.salt.getLength())
        System.arraycopy(hash, 0, salt, 0, salt.size)
        return hash(bytes, salt).contentEquals(hash)
    }
}