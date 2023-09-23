package me.wawwior.utils.hashing;

import java.nio.charset.Charset;

public interface Hash {

    default byte[] hash(String string) {
        return hash(string, Charset.defaultCharset());
    }

    default byte[] hash(String string, Charset charset) {
        return hash(string.getBytes(charset));
    }

    byte[] hash(byte[] bytes);

    boolean compare(byte[] bytes, byte[] hash);

    int hashLength();

}
