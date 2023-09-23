package me.wawwior.utils.hashing.hashes;

import me.wawwior.utils.hashing.Hash;

import java.security.MessageDigest;
import java.util.Arrays;

public class DefaultHash implements Hash {

    private final MessageDigest digest;

    DefaultHash(MessageDigest digest) {
        this.digest = digest;
    }

    @Override
    public byte[] hash(byte[] bytes) {
        return digest.digest(bytes);
    }

    @Override
    public boolean compare(byte[] bytes, byte[] hash) {
        if (hash.length != hashLength())
            return false;
        return Arrays.equals(hash(bytes), hash);
    }

    @Override
    public int hashLength() {
        return digest.getDigestLength();
    }
}
