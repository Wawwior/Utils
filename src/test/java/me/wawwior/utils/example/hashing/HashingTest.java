package me.wawwior.utils.example.hashing;

import me.wawwior.utils.hashing.Hash;
import me.wawwior.utils.hashing.Hashing;
import me.wawwior.utils.hashing.Salt;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;

import java.nio.charset.Charset;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class HashingTest {

    @Test
    void test_hash() {
        Hash sha_256 = Hashing.SHA_2._256.hash();
        byte[] hashBytes = sha_256.hash("Hello World!");
        byte[] hashBytes2 = sha_256.hash("Goodbye World!");
        log(toHex(hashBytes));
        log(toHex(hashBytes2));
        assertTrue(sha_256.compare("Hello World!".getBytes(), hashBytes));
        assertTrue(sha_256.compare("Goodbye World!".getBytes(), hashBytes2));
        assertFalse(sha_256.compare("Hello World!".getBytes(), hashBytes2));
        assertFalse(sha_256.compare("Goodbye World!".getBytes(), hashBytes));
    }

    @Test
    void test_salted() {
        Hash hash = Hashing.SHA_2._256.salted(Salt.random(4));
        byte[] hashBytes = hash.hash("Hello World!");
        log(toHex(hashBytes));
        assertTrue(hash.compare("Hello World!".getBytes(), hashBytes));
    }

    @Test
    void test_salted_length() {
        Hash hash = Hashing.SHA_2._256.salted(Salt.random(4));
        byte[] hashBytes = hash.hash("Hello World!");
        log(toHex(hashBytes));
        assertTrue(hash.compare("Hello World!".getBytes(), hashBytes));
    }

    @AfterAll
    static void afterAll() {
        System.out.println();
    }

    private String toHex(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (byte b : bytes) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }

    private String lastCaller = "";

    private void log(String message) {
        String caller = Thread.currentThread().getStackTrace()[2].getMethodName();
        if (!caller.equals(lastCaller)) {
            System.out.println();
            System.out.println("> " + caller + "(): ");
            lastCaller = caller;
        }
        System.out.println("> " + message);
    }

}
