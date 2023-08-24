package me.wawwior.example.event.events;

import me.wawwior.utils.event.ActionResult;
import me.wawwior.utils.event.implementation.ArrayBackedEvent;
import me.wawwior.utils.event.EventFactory;


public interface IntegerEvent {

    ArrayBackedEvent<IntegerCallback> EVENT = EventFactory.createArrayBackedEvent(IntegerCallback.class, listeners -> {
        if (listeners.length == 0) {
            return i -> ActionResult.PASS;
        }
        if (listeners.length == 1) {
            return i -> listeners[0].post(i);
        }
        return i -> {
            for (IntegerCallback listener : listeners) {
                ActionResult result = listener.post(i);
                if (!result.shouldContinue()) {
                    return result;
                }
            }
            return ActionResult.PASS;
        };
    });

    interface IntegerCallback {

            ActionResult post(int i);

    }

}
