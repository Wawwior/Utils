package me.wawwior.utils.event;

import java.util.Collections;
import java.util.Set;
import java.util.WeakHashMap;
import java.util.function.Function;

public interface EventFactory {

    Set<ArrayBackedEvent<?>> ARRAY_BACKED_EVENTS = Collections.newSetFromMap(new WeakHashMap<>());

    static <T> Event<T> createArrayBackedEvent(Function<Integer, T[]> arrayFactory, Function<T[], T> invokerFactory) {
        ArrayBackedEvent<T> event = new ArrayBackedEvent<>(arrayFactory, invokerFactory);
        ARRAY_BACKED_EVENTS.add(event);
        return event;
    }

    static <T> Event<T> createArrayBackedEvent(Function<Integer, T[]> arrayFactory, T emptyInvoker, Function<T[], T> invokerFactory) {
        return createArrayBackedEvent(arrayFactory, listeners -> {
            if (listeners.length == 0) {
                return emptyInvoker;
            }
            if (listeners.length == 1) {
                return listeners[0];
            }
            return invokerFactory.apply(listeners);
        });
    }

    static void rebuildAllInvokers() {
        ARRAY_BACKED_EVENTS.forEach(ArrayBackedEvent::buildInvoker);
    }

}
