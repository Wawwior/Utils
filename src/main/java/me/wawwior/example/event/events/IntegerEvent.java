package me.wawwior.example.event.events;

import me.wawwior.utils.common.Modifier;
import me.wawwior.utils.event.Event;

public class IntegerEvent extends Event {

    protected int value;

    public IntegerEvent(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public void modifyValue(Modifier<Integer> modifier) {
        value = modifier.modify(value);
    }
}
