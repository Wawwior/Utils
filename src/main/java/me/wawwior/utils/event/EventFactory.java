package me.wawwior.utils.event;

import me.wawwior.utils.event.implementation.ArrayBackedEvent;

import java.util.function.Function;

public interface EventFactory {

    static <T> ArrayBackedEvent<T> createArrayBackedEvent(Class<T> type, Function<T[], T> invokerFactory) {
        return new ArrayBackedEvent<>(type, invokerFactory);
    }

}
