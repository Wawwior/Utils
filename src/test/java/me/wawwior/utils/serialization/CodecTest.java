package me.wawwior.utils.serialization;

import com.google.gson.JsonElement;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

class CodecTest {

    static class TestClass {

        TestClass(int a, int[] b, InnerClass innerClass) {
            this.a = a;
            this.b = b;
            this.innerClass = innerClass;
        }

        int a;

        int[] b;

        InnerClass innerClass;

        public TestClass(int integer, List<Integer> integers, InnerClass innerClass) {
            this(integer, integers.stream().mapToInt(Integer::intValue).toArray(), innerClass);
        }

        int getA() {
            return a;
        }

        int[] getB() {
            return b;
        }

        InnerClass getInnerClass() {
            return innerClass;
        }

        static class InnerClass {

            InnerClass(int c) {
                this.c = c;
            }

            int c;

            int getC() {
                return c;
            }
        }

    }

    @Test
    void test() {


    }


}