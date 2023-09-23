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

        Codec<TestClass> codec = Codec.mapped(mapper -> mapper
                .bind("a", TestClass::getA, Codec.INT)
                .bind("b", o -> IntStream.of(o.getB()).boxed().toList(), Codec.INT.list())
                .bind("inner", TestClass::getInnerClass, Codec.mapped(mapper1 -> mapper1
                        .bind("c", TestClass.InnerClass::getC, Codec.INT)
                        .finalize(TestClass.InnerClass::new))
                ).finalize(TestClass::new));


        TestClass testClass = new TestClass(1, new int[]{ 2, 3, 4 }, new TestClass.InnerClass(5));

        JsonElement jsonElement = codec.encode(testClass);

        System.out.println(jsonElement);

        DataResult<TestClass> result = codec.decode(jsonElement);

        TestClass resultClass = result.result(Assertions::fail);

        assertEquals(testClass.getA(), resultClass.getA());
        assertArrayEquals(testClass.getB(), resultClass.getB());
        assertEquals(testClass.getInnerClass().getC(), resultClass.getInnerClass().getC());

    }


}