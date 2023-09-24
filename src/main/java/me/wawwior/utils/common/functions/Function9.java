package me.wawwior.utils.common.functions;

public interface Function9<P1, P2, P3, P4, P5, P6, P7, P8, P9, R> {

    R apply(P1 p1, P2 p2, P3 p3, P4 p4, P5 p5, P6 p6, P7 p7, P8 p8, P9 p9);

    default Function8<P1, P2, P3, P4, P5, P6, P7, P8, R> bind(P9 p9) {
    return (P1 p1, P2 p2, P3 p3, P4 p4, P5 p5, P6 p6, P7 p7, P8 p8) -> apply(p1, p2, p3, p4, p5, p6, p7, p8, p9);
}

}
