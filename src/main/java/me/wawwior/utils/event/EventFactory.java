package me.wawwior.utils.event;

import java.util.function.Function;

public interface EventFactory {

    static <T> Event<T> create(Class<T> type, Function<T[], T> invokerFactory) {
        return new Event<>(type, invokerFactory);
    }

}
