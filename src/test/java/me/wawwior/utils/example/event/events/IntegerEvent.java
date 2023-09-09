package me.wawwior.utils.example.event.events;

import me.wawwior.utils.event.ActionResult;
import me.wawwior.utils.event.Event;
import me.wawwior.utils.event.EventFactory;


public interface IntegerEvent {

    Event<IntegerCallback> EVENT = EventFactory.createArrayBackedEvent(IntegerCallback[]::new, i -> ActionResult.PASS, listeners -> i -> {
        for (IntegerCallback listener : listeners) {
            ActionResult result = listener.post(i);
            if (!result.shouldContinue()) {
                return result;
            }
        }
        return ActionResult.PASS;
    });

    interface IntegerCallback {

            ActionResult post(int i);

    }

}
