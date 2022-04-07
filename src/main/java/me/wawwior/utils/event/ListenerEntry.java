package me.wawwior.utils.event;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public record ListenerEntry(IEventListener listener, Method method, int priority) {

    public void post(Event event) {
        try {
            if (method.getParameterTypes()[0].equals(event.getClass())) {
                method.invoke(listener, event);
            }
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }

}
