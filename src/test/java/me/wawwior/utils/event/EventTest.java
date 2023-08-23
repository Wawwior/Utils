package me.wawwior.utils.event;

import me.wawwior.example.event.events.IntegerEvent;
import me.wawwior.example.event.v2.callbacks.IntegerCallback;
import me.wawwior.example.event.v3.events.IntegerEventV3;
import me.wawwior.utils.event.v2.ActionResult;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class EventTest {

    static EventBus eventBus;

    static int repetitions;

    int value;


    @BeforeAll
    static void register() {
        int magnitude = 8;
        repetitions = (int) Math.pow(10, magnitude);
        eventBus = new EventBus();
        eventBus.register(new IEventListener() {
            @Subscribe
            public void onIntEvent(IntegerEvent event) {}
        });
        IntegerCallback.INSTANCE.register(ci -> ActionResult.CONTINUE);
        IntegerEventV3.EVENT.register(i -> ActionResult.CONTINUE);
    }

    @BeforeEach
    void randomize() {
        value = (int) (Math.random() * 100);
    }

    @Test
    void testV1() {
        for (int i = 0; i < repetitions; i++) {
            eventBus.post(new IntegerEvent(value));
        }
    }

    @Test
    void testV2() {
        for (int i = 0; i < repetitions; i++) {
            IntegerCallback.INSTANCE.post(new IntegerCallback.IntegerCallbackInfo(value));
        }
    }

    @Test
    void testV3() {
        for (int i = 0; i < repetitions; i++) {
            IntegerEventV3.EVENT.invoker().post(value);
        }
    }

}