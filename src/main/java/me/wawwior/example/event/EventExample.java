package me.wawwior.example.event;

import me.wawwior.example.event.events.IntegerEvent;
import me.wawwior.utils.event.*;

public class EventExample implements IEventListener {

    @Subscribe(Priority.HIGH)
    public void changeIntEvent(IntegerEvent event) {
        System.out.println(event.getValue());
        event.modifyValue(i -> i * 2);
    }

    @Subscribe
    public void onIntEvent(IntegerEvent event) {
        System.out.println(event.getValue());
    }

    public static void main(String[] args) {
        EventBus bus = new EventBus();
        bus.register(new EventExample());
        bus.post(new IntegerEvent(10));
        bus.post(new Event());
    }

}
