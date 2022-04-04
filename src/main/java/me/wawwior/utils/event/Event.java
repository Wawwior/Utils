package me.wawwior.utils.event;

public abstract class Event {

    protected boolean canceled = false;

    public boolean isCanceled() {
        return canceled;
    }

}
