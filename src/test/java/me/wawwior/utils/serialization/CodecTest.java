package me.wawwior.utils.serialization;

import com.google.gson.JsonElement;
import me.wawwior.utils.serialization.CodecTest.TestClass.InnerClass;
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
                .bind(Codec.INT.field("a").bind(TestClass::getA))
                .bind(Codec.INT.list().field("b").bind(o -> IntStream.of(o.getB()).boxed().toList()))
                .bind(Codec.INT.field("c").bind(o -> o.getInnerClass().getC()))
                .finalize((a, b, c) -> new TestClass(a, b, new InnerClass(c))));

        TestClass testClass = new TestClass(1, Arrays.asList(2, 3, 4), new InnerClass(5));

        JsonElement element = codec.encode(testClass);

        System.out.println(element);

        DataResult<TestClass> result = codec.decode(element);

        TestClass resultValue = result.result(Assertions::fail);

        assertEquals(testClass.getA(), resultValue.getA());
        assertArrayEquals(testClass.getB(), resultValue.getB());
        assertEquals(testClass.getInnerClass().getC(), resultValue.getInnerClass().getC());
    }


}