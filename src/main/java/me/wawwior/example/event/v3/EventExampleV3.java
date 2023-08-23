package me.wawwior.example.event.v3;

import me.wawwior.example.event.v3.events.IntegerEventV3;
import me.wawwior.utils.common.Timer;
import me.wawwior.utils.event.v2.ActionResult;

public class EventExampleV3 {

    public static ActionResult onIntEvent(int i) {
        System.out.println(i);
        return ActionResult.CONTINUE;
    }

    public static void main(String[] args) {
        Timer timer = new Timer();
        Timer timeAll = new Timer();
        IntegerEventV3.EVENT.register(EventExampleV3::onIntEvent);
        timer.log(System.out, s -> "Listener registration: " + s + "\n").reset();
        IntegerEventV3.EVENT.invoker().post(0);
        timer.log(System.out, s -> "Event posting: " + s + "\n");
        timeAll.log(System.out, s -> "Total time: " + s + "\n");
    }

}
