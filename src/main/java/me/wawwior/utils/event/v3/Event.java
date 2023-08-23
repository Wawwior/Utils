package me.wawwior.utils.event.v3;

import java.lang.reflect.Array;
import java.util.function.Function;

public class Event<T> {

    private T[] listeners;
    private final Function<T[], T> invokerFactory;

    private T invoker;

    @SuppressWarnings("unchecked")
    Event(Class<T> type, Function<T[], T> invokerFactory) {
        listeners = (T[]) Array.newInstance(type, 0);
        this.invokerFactory = invokerFactory;
        buildInvoker();
    }

    private void buildInvoker() {
        invoker = invokerFactory.apply(listeners);
    }

    @SuppressWarnings("unchecked")
    public void register(T listener) {
        T[] newListeners = (T[]) Array.newInstance(listeners.getClass().getComponentType(), listeners.length + 1);
        System.arraycopy(listeners, 0, newListeners, 0, listeners.length);
        newListeners[listeners.length] = listener;
        listeners = newListeners;
        buildInvoker();
    }


    public T invoker() {
        return invoker;
    }

}