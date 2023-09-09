package me.wawwior.utils.hashing.hashes

import me.wawwior.utils.hashing.HashProvider
import me.wawwior.utils.hashing.Salt
import java.security.MessageDigest

class DefaultHashProvider(algorithm: String) : HashProvider {

    private val messageDigest = MessageDigest.getInstance(algorithm)

    private val instance = DefaultHash(messageDigest)

    override fun hash() = instance

    override fun salted(salt: Salt) = DefaultSaltedHash(messageDigest, salt)
}