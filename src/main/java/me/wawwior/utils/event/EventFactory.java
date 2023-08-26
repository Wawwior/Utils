package me.wawwior.utils.event;

import com.google.common.collect.MapMaker;
import me.wawwior.utils.event.implementation.ArrayBackedEvent;

import java.util.Collections;
import java.util.Set;
import java.util.function.Function;

public final class EventFactory {

    private static final Set<ArrayBackedEvent<?>> ARRAY_BACKED_EVENTS = Collections.newSetFromMap(new MapMaker().weakKeys().makeMap());

    public static <T> Event<T> createArrayBackedEvent(Class<T> type, Function<T[], T> invokerFactory) {
        return new ArrayBackedEvent<>(type, invokerFactory);
    }

    public static <T> Event<T> createArrayBackedEvent(Class<T> type, T emptyInvoker, Function<T[], T> invokerFactory) {
        return createArrayBackedEvent(type, listeners -> {
            if (listeners.length == 0) {
                return emptyInvoker;
            }
            if (listeners.length == 1) {
                return listeners[0];
            }
            return invokerFactory.apply(listeners);
        });
    }

    public static void rebuildInvokers() {
        ARRAY_BACKED_EVENTS.forEach(ArrayBackedEvent::buildInvoker);
    }

}
