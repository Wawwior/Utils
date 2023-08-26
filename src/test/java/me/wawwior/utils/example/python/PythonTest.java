package me.wawwior.utils.example.python;

import me.wawwior.utils.common.Timer;
import me.wawwior.utils.python.IScope;
import me.wawwior.utils.python.Interpreter;
import me.wawwior.utils.python.PyVar;
import me.wawwior.utils.python.ScopeReference;
import org.junit.jupiter.api.Test;

import java.util.concurrent.TimeUnit;
import java.util.function.Function;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PythonTest implements IScope {

    @PyVar
    public int x = 10;

    @PyVar(name = "y")
    public int myInt = 2;

    @Test
    void python_test() {

        Timer timer = new Timer().start();

        PythonTest pythonTest = new PythonTest();

        timer.log(System.out, toMillis(n -> "PythonTest Construction: " + n + "ms\n")).reset();

        Interpreter interpreter = new Interpreter();

        timer.log(System.out, toMillis(n -> "Interpreter Construction: " + n + "ms\n")).reset();

        ScopeReference pointer = new ScopeReference(pythonTest);

        timer.log(System.out, toMillis(n -> "ScopeReference Construction: " + n + "ms\n")).reset();

        interpreter.execute("y = x * x", pointer);

        timer.log(System.out, toMillis(n -> "Code Execution: " + n + "ms\n")).reset();

        assertEquals(100, pythonTest.myInt);
    }

    static Function<Long, String> toMillis(Function<Double, String> function) {
        return n -> function.apply(TimeUnit.NANOSECONDS.toMicros(n) / 1000.0);
    }

}
