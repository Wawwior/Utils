package me.wawwior.utils.event;

public class CancelableEvent extends Event {

    public void setCanceled(boolean canceled) {
        this.canceled = canceled;
    }

}
