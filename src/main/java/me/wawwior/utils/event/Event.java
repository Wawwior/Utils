package me.wawwior.utils.event;

public interface Event<T> {

    void register(T listener);

    T invoker();

}
