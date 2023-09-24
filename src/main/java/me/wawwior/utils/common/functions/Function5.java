package me.wawwior.utils.common.functions;

public interface Function5<P1, P2, P3, P4, P5, R> {

    R apply(P1 p1, P2 p2, P3 p3, P4 p4, P5 p5);

    default Function4<P1, P2, P3, P4, R> bind(P5 p5) {
    return (P1 p1, P2 p2, P3 p3, P4 p4) -> apply(p1, p2, p3, p4, p5);
}

}
