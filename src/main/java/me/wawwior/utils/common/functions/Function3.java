package me.wawwior.utils.common.functions;

public interface Function3<P1, P2, P3, R> {

    R apply(P1 p1, P2 p2, P3 p3);

    default Function2<P1, P2, R> bind(P3 p3) {
    return (P1 p1, P2 p2) -> apply(p1, p2, p3);
}

}
