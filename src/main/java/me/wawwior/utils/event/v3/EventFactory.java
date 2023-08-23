package me.wawwior.utils.event.v3;

import java.util.function.Function;

public class EventFactory {

    public static <T> Event<T> create(Class<T> type, Function<T[], T> invokerFactory) {
        return new Event<>(type, invokerFactory);
    }

}
