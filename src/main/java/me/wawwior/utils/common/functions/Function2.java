package me.wawwior.utils.common.functions;

import java.util.function.Function;

public interface Function2<P1, P2, R> {

    R apply(P1 p1, P2 p2);

    default Function<P1, R> bind(P2 p2) {
    return (P1 p1) -> apply(p1, p2);
}

}
