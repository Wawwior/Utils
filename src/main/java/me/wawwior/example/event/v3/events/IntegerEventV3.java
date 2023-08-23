package me.wawwior.example.event.v3.events;

import me.wawwior.utils.event.v2.ActionResult;
import me.wawwior.utils.event.v3.Event;
import me.wawwior.utils.event.v3.EventFactory;


public interface IntegerEventV3 {

    Event<IntegerCallback> EVENT = EventFactory.create(IntegerCallback.class, listeners -> {
        if (listeners.length == 0) {
            return i -> ActionResult.CONTINUE;
        }
        if (listeners.length == 1) {
            return i -> listeners[0].post(i);
        }
        return i -> {
            for (IntegerCallback listener : listeners) {
                ActionResult result = listener.post(i);
                if (result != ActionResult.CONTINUE) {
                    return result;
                }
            }
            return ActionResult.CONTINUE;
        };
    });

    interface IntegerCallback {

            ActionResult post(int i);

    }

}
