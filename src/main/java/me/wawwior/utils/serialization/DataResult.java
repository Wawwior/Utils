package me.wawwior.utils.serialization;

import org.jetbrains.annotations.NotNull;

import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;

public interface DataResult<T> {

    T resultOr(T or);

    Optional<String> error();

    T result(Consumer<String> onError);

    static <T> DataResult<T> success(@NotNull T value) {
        return new DataResult<>() {

            @Override
            public T resultOr(T or) {
                return value;
            }

            @Override
            public Optional<String> error() {
                return Optional.empty();
            }

            @Override
            public T result(Consumer<String> onError) {
                return value;
            }

            @Override
            public <U> DataResult<U> map(Function<T, U> func) {
                return DataResult.success(func.apply(value));
            }
        };
    }

    static <T> DataResult<T> error(String error) {
        return new DataResult<>() {

            @Override
            public T resultOr(T or) {
                return or;
            }

            @Override
            public Optional<String> error() {
                return Optional.of(error);
            }

            @Override
            public T result(Consumer<String> onError) {
                onError.accept(error);
                return null;
            }

            @Override
            public <U> DataResult<U> map(Function<T, U> func) {
                return DataResult.error(error);
            }
        };
    }

    static <T> DataResult<T> partial(T part, String error) {
        return new DataResult<>() {

            @Override
            public T resultOr(T or) {
                return or;
            }

            @Override
            public Optional<String> error() {
                return Optional.of(error);
            }

            @Override
            public T result(Consumer<String> onError) {
                onError.accept(error);
                return part;
            }

            @Override
            public <U> DataResult<U> map(Function<T, U> func) {
                return DataResult.partial(func.apply(part), error);
            }

        };
    }

    <U> DataResult<U> map(Function<T, U> func);

}
