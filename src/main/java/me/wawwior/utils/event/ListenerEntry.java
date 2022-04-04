package me.wawwior.utils.event;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public record ListenerEntry(IEventListener listener, Method method, int priority) {

    public void post(Event event) {
        try {
            method.invoke(listener, event);
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }

}
