package me.wawwior.utils.hashing.hashes;

import me.wawwior.utils.hashing.Hash;
import me.wawwior.utils.hashing.HashProvider;
import me.wawwior.utils.hashing.Salt;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class DefaultHashProvider implements HashProvider {

    private final MessageDigest digest;

    private final Hash instance;

    public DefaultHashProvider(String algorithm) {
        try {
            digest = MessageDigest.getInstance(algorithm);
            instance = new DefaultHash(digest);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Hash hash() {
        return instance;
    }

    @Override
    public Hash salted(Salt salt) {
        return new DefaultSaltedHash(digest, salt);
    }
}
