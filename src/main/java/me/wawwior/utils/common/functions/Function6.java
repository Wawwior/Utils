package me.wawwior.utils.common.functions;

public interface Function6<P1, P2, P3, P4, P5, P6, R> {

    R apply(P1 p1, P2 p2, P3 p3, P4 p4, P5 p5, P6 p6);

    default Function5<P1, P2, P3, P4, P5, R> bind(P6 p6) {
    return (P1 p1, P2 p2, P3 p3, P4 p4, P5 p5) -> apply(p1, p2, p3, p4, p5, p6);
}

}
