package me.wawwior.utils.math;


/**
 *
 * Represents an associative operation on two operands of type {@code T} that produces a result of the same type.
 *
 * @param <T> type of value
 */
public interface BinaryOperation<T> {

    T apply(T a, T b);

}
