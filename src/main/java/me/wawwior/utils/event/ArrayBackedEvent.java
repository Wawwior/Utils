package me.wawwior.utils.event;

import java.util.function.Function;

public class ArrayBackedEvent<T> implements Event<T> {

    private final Function<Integer, T[]> arrayFactory;
    private final Function<T[], T> invokerFactory;

    private T[] listeners;

    private T invoker;

    ArrayBackedEvent(Function<Integer, T[]> arrayFactory, Function<T[], T> invokerFactory) {
        this.arrayFactory = arrayFactory;
        this.invokerFactory = invokerFactory;

        this.listeners = arrayFactory.apply(0);
        this.invoker = invokerFactory.apply(this.listeners);

    }

    public void buildInvoker() {
        invoker = invokerFactory.apply(listeners);
    }

    @Override
    public void register(T listener) {
        T[] newListeners = arrayFactory.apply(listeners.length + 1);
        System.arraycopy(listeners, 0, newListeners, 0, listeners.length);
        newListeners[listeners.length] = listener;
        listeners = newListeners;
        buildInvoker();
    }

    @Override
    public T invoker() {
        return invoker;
    }
}
