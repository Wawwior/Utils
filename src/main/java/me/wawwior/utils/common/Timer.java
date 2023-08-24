package me.wawwior.utils.common;

import java.io.IOException;
import java.io.OutputStream;
import java.util.function.Function;

public class Timer {

    private long start;
    private long accumulated = 0;

    private boolean running = false;

    public Timer() {
        start = System.nanoTime();
    }

    public long get() {
        return accumulated + (running ? System.nanoTime() - start : 0);
    }

    public Timer start() {
        if (!running) {
            start = System.nanoTime();
            running = true;
        }
        return this;
    }

    public Timer stop() {
        if (running) {
            accumulated += System.nanoTime() - start;
            running = false;
        }
        return this;
    }

    public void reset() {
        start = System.nanoTime();
    }


    public Timer log(OutputStream stream) {
        return log(stream, s -> "Logged after: " + s + "\n");
    }

    public Timer log(OutputStream stream, Function<Long, String> modifier) {
        try {
            stream.write(modifier.apply(get()).getBytes());
        } catch (IOException e) {
            System.out.println("OutputStream \"" + stream + "\" refused writing.");
        }
        return this;
    }


}
