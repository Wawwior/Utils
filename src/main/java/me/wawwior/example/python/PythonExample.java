package me.wawwior.example.python;

import me.wawwior.utils.common.Timer;
import me.wawwior.utils.python.Interpreter;
import me.wawwior.utils.python.PyVar;
import me.wawwior.utils.python.IScope;
import me.wawwior.utils.python.ScopeReference;

public class PythonExample implements IScope {

    @PyVar
    public int x = 10;

    @PyVar(name = "y")
    public int myInt = 2;

    public static void main(String[] args) {
        Timer timer = new Timer();
        PythonExample example = new PythonExample();

        timer.log(System.out, time -> "PythonExample Construction: " + time + "\n\n").reset();

        Interpreter interpreter = new Interpreter();

        timer.log(System.out, time -> "Interpreter Construction: " + time + "\n\n").reset();

        ScopeReference pointer = new ScopeReference(example);

        timer.log(System.out, time -> "ScopeReference Construction: " + time + "\n\n").reset();

        interpreter.execute("y = x * x", pointer);

        timer.log(System.out, time -> "Code Execution: " + time + "\n\n").reset();

        System.out.print("example.myInt == ");
        System.out.println(example.myInt);

    }

}
