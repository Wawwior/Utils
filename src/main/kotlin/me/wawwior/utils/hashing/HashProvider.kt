package me.wawwior.utils.hashing

interface HashProvider {

    fun hash(): Hash

    fun salted(salt: Salt): Hash

}