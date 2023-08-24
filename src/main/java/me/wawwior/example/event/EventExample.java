package me.wawwior.example.event;

import me.wawwior.example.event.events.IntegerEvent;
import me.wawwior.utils.common.Timer;
import me.wawwior.utils.event.ActionResult;

import java.util.concurrent.TimeUnit;

public class EventExample {

    public static ActionResult onIntEvent(int i) {
        System.out.println(i);
        return ActionResult.PASS;
    }

    public static void main(String[] args) {
        Timer timer = new Timer().start();
        Timer timeAll = new Timer().start();
        IntegerEvent.EVENT.register(EventExample::onIntEvent);
        timer.log(System.out, n -> "Listener registration: " + TimeUnit.NANOSECONDS.toMillis(n) + "ms\n").reset();
        IntegerEvent.EVENT.invoker().post(0);
        timer.log(System.out, n -> "Event posting: " + TimeUnit.NANOSECONDS.toMillis(n) + "ms\n");
        timeAll.log(System.out, n -> "Total time: " + TimeUnit.NANOSECONDS.toMillis(n) + "ms\n");
    }

}
