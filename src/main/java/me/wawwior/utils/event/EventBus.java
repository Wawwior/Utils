package me.wawwior.utils.event;

import me.wawwior.utils.reflection.CloneFactory;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class EventBus {

    List<ListenerEntry> listenerEntries = new ArrayList<>();

    boolean sorted = false;

    int maxParallelThreads = 10;

    public EventBus() {}

    public EventBus(int maxParallelThreads) {
        this.maxParallelThreads = maxParallelThreads;
    }

    public void setMaxParallelThreads(int maxParallelThreads) {
        this.maxParallelThreads = maxParallelThreads;
    }

    public void register(IEventListener listener) {
        listenerEntries.addAll(entries(listener));
        sorted = false;
    }

    public boolean post(Event event) {

        if (!sorted) listenerEntries.sort(Comparator.comparingInt(ListenerEntry::priority));

        sorted = true;

        listenerEntries.forEach(l -> {
            if (!event.isCanceled()) {
                l.post(event);
            }
        });
        return event.isCanceled();
    }

    public void postParallel(Event event) {
        ThreadPoolExecutor pool = new ThreadPoolExecutor(maxParallelThreads, maxParallelThreads, 0, TimeUnit.SECONDS, new ArrayBlockingQueue<>(maxParallelThreads * 10));
        listenerEntries.forEach(l -> {
            pool.execute(() -> {
                try {
                    l.post(CloneFactory.clone(event));
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            });
        });
    }

    private static List<ListenerEntry> entries(IEventListener listener) {
        List<ListenerEntry> entries = new ArrayList<>();

        for (Method m : listener.getClass().getMethods()) {
            if (
                    m.getReturnType().getName().equalsIgnoreCase("void")
                    && m.getParameterCount() == 1
                    && Event.class.isAssignableFrom(m.getParameterTypes()[0])
                    && m.getAnnotation(Subscribe.class) != null
            ) {
                entries.add(new ListenerEntry(listener, m, m.getAnnotation(Subscribe.class).value()));
            }
        }

        return entries;
    }

}
