package me.wawwior.utils.python;

import org.python.util.PythonInterpreter;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.logging.Logger;

public class ScopeReference {

    private final HashMap<Field, String> fields = new HashMap<>();

    private final IScope scope;

    public ScopeReference(IScope scope) {
        this.scope = scope;
        for (Field f : scope.getClass().getFields()) {
            PyVar var = f.getAnnotation(PyVar.class);
            if (var != null) {
                fields.put(f, var.name().equalsIgnoreCase("") ? f.getName() : var.name());
            }
        }
    }

    public void point(PythonInterpreter interpreter) {
        fields.forEach((f, s) -> {
            try {
                interpreter.set(s, f.get(scope));
            } catch (IllegalAccessException e) {
                Logger.getAnonymousLogger().warning("Failed to point " + f.getName() + " to " + s);
            }
        });
    }

    public void resolve(PythonInterpreter interpreter) {
        fields.forEach((f, s) -> {
            try {
                f.set(scope, interpreter.get(s).__tojava__(f.getType()));
            } catch (IllegalAccessException e) {
                Logger.getAnonymousLogger().warning("Failed to resolve " + f.getName() + " from " + s);
            }
        });
    }

}
