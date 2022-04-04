package me.wawwior.utils.common;

import java.io.IOException;
import java.io.OutputStream;

public class Timer {

    private long start;

    public Timer() {
        start = System.currentTimeMillis();
    }

    public Timer log(OutputStream stream) {
        try {
            stream.write(("Logged after: " + (System.currentTimeMillis() - start) + "ms\n").getBytes());
        } catch (IOException e) {
            System.out.println("OutputStream \"" + stream + "\" refused writing.");
        }
        return this;
    }

    public Timer log(OutputStream stream, Modifier<String> modifier) {
        try {
            stream.write(modifier.modify(System.currentTimeMillis() - start + "ms").getBytes());
        } catch (IOException e) {
            System.out.println("OutputStream \"" + stream + "\" refused writing.");
        }
        return this;
    }

    public void reset() {
        start = System.currentTimeMillis();
    }
}
