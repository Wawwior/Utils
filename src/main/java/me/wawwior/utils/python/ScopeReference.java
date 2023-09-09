package me.wawwior.utils.python;

import org.python.util.PythonInterpreter;

import java.lang.reflect.Field;
import java.util.Arrays;

public class ScopeReference {

    private final Field[] fields;
    private final String[] fieldNames;

    private final IScope scope;

    public ScopeReference(IScope scope) {
        this.scope = scope;
        fields = Arrays.stream(scope.getClass().getFields()).filter(
                f -> f.getAnnotation(PyVar.class) != null
        ).toArray(Field[]::new);
        fieldNames = Arrays.stream(fields).map(
                f -> f.getAnnotation(PyVar.class).name().equalsIgnoreCase("") ? f.getName() : f.getAnnotation(PyVar.class).name()
        ).toArray(String[]::new);
    }

    public void point(PythonInterpreter interpreter) {
        try {
            for (int i = 0; i < fields.length; i++) {
                interpreter.set(fieldNames[i], fields[i].get(scope));
            }
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    public void resolve(PythonInterpreter interpreter) {

        try {
            for (int i = 0; i < fields.length; i++) {
                fields[i].set(scope, interpreter.get(fieldNames[i]).__tojava__(fields[i].getType()));
            }
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

}
