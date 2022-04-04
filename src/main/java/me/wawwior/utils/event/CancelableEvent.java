package me.wawwior.utils.event;

public abstract class CancelableEvent extends Event {

    public void setCanceled(boolean canceled) {
        this.canceled = canceled;
    }

}
