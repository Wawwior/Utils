package me.wawwior.utils.hashing

import java.util.*

interface Salt {

    companion object {

        @JvmStatic
        fun random(length: Int): Salt {
            return random(length, Random())
        }

        @JvmStatic
        fun random(length: Int, seed: Long): Salt {
            return random(length, Random(seed))
        }

        private fun random(length: Int, random: Random): Salt {
            return object : Salt {
                override fun getSalt(): ByteArray {
                    val salt = ByteArray(length)
                    random.nextBytes(salt)
                    return salt
                }

                override fun getLength(): Int {
                    return length
                }
            }
        }

        @JvmStatic
        fun fixed(salt: ByteArray): Salt {
            return object : Salt {
                override fun getSalt(): ByteArray {
                    return salt
                }

                override fun getLength(): Int {
                    return salt.size
                }
            }
        }

        @JvmStatic
        fun none(): Salt {
            return fixed(ByteArray(0))
        }
    }

    fun getSalt(): ByteArray

    fun getLength(): Int

}