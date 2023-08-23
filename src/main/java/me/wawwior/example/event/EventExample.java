package me.wawwior.example.event;

import me.wawwior.example.event.events.IntegerEvent;
import me.wawwior.utils.common.Timer;
import me.wawwior.utils.event.*;

public class EventExample implements IEventListener {

    @Subscribe
    public void onIntEvent(IntegerEvent event) {
        System.out.println(event.getValue());
    }

    public static void main(String[] args) {
        Timer timer = new Timer();
        Timer timeAll = new Timer();
        EventBus bus = new EventBus();
        timer.log(System.out, s -> "EventBus init: " + s + "\n").reset();
        bus.register(new EventExample());
        timer.log(System.out, s -> "Listener registration: " + s + "\n").reset();
        bus.post(new IntegerEvent(10));
        timer.log(System.out, s -> "Event posting: " + s + "\n");
        timeAll.log(System.out, s -> "Total time: " + s + "\n");
    }

}
