package me.wawwior.utils.hashing;

import java.util.Random;

public interface Salt {

    static Salt random(int length) {
        return random(length, new Random());
    }

    static Salt random(int length, long seed) {
        return random(length, new Random(seed));
    }

    static Salt random(int length, Random random) {
        return new Salt() {
            @Override
            public byte[] getSalt() {
                byte[] salt = new byte[length];
                random.nextBytes(salt);
                return salt;
            }

            @Override
            public int getLength() {
                return length;
            }
        };
    }

    static Salt fixed(byte[] salt) {
        return new Salt() {
            @Override
            public byte[] getSalt() {
                return salt;
            }

            @Override
            public int getLength() {
                return salt.length;
            }
        };
    }

    static Salt none() {
        return fixed(new byte[0]);
    }

    byte[] getSalt();

    int getLength();

}
