package me.wawwior.utils.example.event;

import me.wawwior.utils.event.ActionResult;
import me.wawwior.utils.example.event.events.IntegerEvent;
import me.wawwior.utils.common.Timer;
import org.junit.jupiter.api.Test;

import java.util.concurrent.TimeUnit;
import java.util.function.Function;

class EventTest {

    public static ActionResult onIntEvent(int i) {
        System.out.println(i);
        return ActionResult.PASS;
    }


    @Test
    void event_test() {
        Timer timer = new Timer().start();
        Timer timeAll = new Timer().start();
        IntegerEvent.EVENT.register(EventTest::onIntEvent);
        timer.log(System.out, toMillis(n -> "Listener registration: " + n + "ms\n")).reset();
        IntegerEvent.EVENT.invoker().post(0);
        timer.log(System.out, toMillis(n -> "Event posting: " + n + "ms\n"));
        timeAll.log(System.out, toMillis(n -> "Total time: " + n + "ms\n"));
    }

    static Function<Long, String> toMillis(Function<Double, String> function) {
        return n -> function.apply(TimeUnit.NANOSECONDS.toMicros(n) / 1000.0);
    }

}
