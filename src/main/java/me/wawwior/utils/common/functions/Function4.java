package me.wawwior.utils.common.functions;

public interface Function4<P1, P2, P3, P4, R> {

    R apply(P1 p1, P2 p2, P3 p3, P4 p4);

    default Function3<P1, P2, P3, R> bind(P4 p4) {
    return (P1 p1, P2 p2, P3 p3) -> apply(p1, p2, p3, p4);
}

}
