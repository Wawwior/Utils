package me.wawwior.utils.python;

import org.python.core.PyCode;
import org.python.util.PythonInterpreter;

/**
 * Easy access interface for {@link PythonInterpreter}.
 */
public class Interpreter {

    PythonInterpreter interpreter = new PythonInterpreter();

    /**
     * Directly execute compiled python code in the interpreter.
     *
     * Imports have to be included in the code!
     *
     * @param code {@link PyCode} to execute
     */
    public void execute(PyCode code) {
        interpreter.exec(code);
        interpreter.cleanup();
    }

    /**
     * Directly execute compiled python code in the interpreter,
     * with reference to an {@link IScope} for variables.
     *
     * Imports have to be included in the code!
     *
     * @param code {@link PyCode} to execute
     * @param reference {@link ScopeReference} for variables
     */
    public void execute(PyCode code, ScopeReference reference) {
        reference.point(interpreter);
        execute(code);
        reference.resolve(interpreter);
    }

    /**
     * Compile and execute code in String format in the interpreter.
     *
     * @param code {@link PyCode} to execute
     */
    public void execute(String code) {
        execute(interpreter.compile(code));
    }

    /**
     * Compile and execute code in String format in the interpreter,
     * with reference to an {@link IScope} for variables.
     *
     * @param code {@link PyCode} to execute
     * @param pointer {@link ScopeReference} for variables
     */
    public void execute(String code, ScopeReference pointer) {
        execute(interpreter.compile(code), pointer);
    }

}
