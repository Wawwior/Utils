package me.wawwior.utils.hashing.hashes;

import me.wawwior.utils.hashing.Hash;
import me.wawwior.utils.hashing.Salt;

import java.security.MessageDigest;
import java.util.Arrays;

public class DefaultSaltedHash implements Hash {

    private final MessageDigest digest;
    private final Salt salt;

    DefaultSaltedHash(MessageDigest digest, Salt salt) {
        this.digest = digest;
        this.salt = salt;
    }

    @Override
    public byte[] hash(byte[] bytes) {
        if (salt.getLength() > 0) {
            return hash(bytes, salt.getSalt());
        }
        return digest.digest(bytes);
    }

    private byte[] hash(byte[] bytes, byte[] salt) {
        byte[] salted = new byte[bytes.length + salt.length];
        System.arraycopy(bytes, 0, salted, 0, bytes.length);
        System.arraycopy(salt, 0, salted, bytes.length, salt.length);
        byte[] saltedHash = digest.digest(salted);
        byte[] result = new byte[saltedHash.length + salt.length];
        System.arraycopy(salt, 0, result, 0, salt.length);
        System.arraycopy(saltedHash, 0, result, salt.length, saltedHash.length);
        return result;
    }

    @Override
    public boolean compare(byte[] bytes, byte[] hash) {
        if (hash.length != hashLength())
            return false;
        if (salt.getLength() == 0) {
            return Arrays.equals(digest.digest(bytes), hash);
        }
        byte[] salt = new byte[this.salt.getLength()];
        System.arraycopy(hash, 0, salt, 0, salt.length);
        return Arrays.equals(hash(bytes, salt), hash);
    }

    @Override
    public int hashLength() {
        return digest.getDigestLength() + salt.getLength();
    }
}
