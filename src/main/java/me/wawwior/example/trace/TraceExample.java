package me.wawwior.example.trace;

import me.wawwior.utils.common.Timer;
import me.wawwior.utils.trace.Traced;

public class TraceExample {

    public static void main(String[] args) {
        Timer timer = new Timer();
        Traced<String> stringTraced = new Traced<>("Hello World");
        System.out.println(stringTraced);
        timer.log(System.out);
    }

}
