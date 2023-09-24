package me.wawwior.utils.serialization;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import me.wawwior.utils.common.functions.Function2;
import me.wawwior.utils.common.functions.Function3;
import me.wawwior.utils.serialization.codec.BoundFieldCodec;

import java.util.function.Function;

public class CodecMapper<R> {

    public <P1> Part1<P1, R> bind(BoundFieldCodec<P1, R> codec) {
        return new Part1<>(codec);
    }

    static abstract class Part<P1, R, F, C extends Part<?, R, ?, ?>> implements Function<F, DataResult<R>> {

        private final BoundFieldCodec<P1, R> codec;
        protected final C child;
        protected DataResult<P1> result;

        Part(BoundFieldCodec<P1, R> codec, C child) {
            this.codec = codec;
            this.child = child;
        }

        public Codec<R> finalize(F f) {
            return new Codec<>() {
                @Override
                public JsonElement encode(R object) {
                    JsonObject jsonObject = new JsonObject();
                    encodeTo(jsonObject.getAsJsonObject(), object);
                    return jsonObject;
                }

                @Override
                public DataResult<R> decode(JsonElement element) {
                    if (!element.isJsonObject()) {
                        return DataResult.error("Not a JSON object");
                    }
                    JsonObject jsonObject = element.getAsJsonObject();
                    decodeFrom(jsonObject);
                    return apply(f);
                }
            };
        }

        public void encodeTo(JsonObject jsonObject, R object) {
            if (child != null) child.encodeTo(jsonObject, object);
            codec.encodeTo(object, jsonObject);
        }

        public void decodeFrom(JsonObject jsonObject) {
            if (child != null) child.decodeFrom(jsonObject);
            result = codec.decodeFrom(jsonObject);
        }

        @Override
        public DataResult<R> apply(F f) {
            return null;
        }
    }

    public static class Part1<P1, R> extends Part<P1, R, Function<P1, R>, Part<?, R, ?, ?>> {

        Part1(BoundFieldCodec<P1, R> codec) {
            super(codec, null);
        }

        @Override
        public DataResult<R> apply(Function<P1, R> p1RFunction) {
            return this.result.map(p1RFunction);
        }

        public <P2> Part2<P1, P2, R> bind(BoundFieldCodec<P2, R> codec) {
            return new Part2<>(codec, this);
        }

    }

    public static class Part2<P1, P2, R> extends Part<P2, R, Function2<P1, P2, R>, Part1<P1, R>> {

        Part2(BoundFieldCodec<P2, R> codec, Part1<P1, R> child) {
            super(codec, child);
        }

        @Override
        public DataResult<R> apply(Function2<P1, P2, R> func2) {
            return this.child.apply(func2.bind(this.result.result(System.out::println)));
        }

        public <P3> Part3<P1, P2, P3, R> bind(BoundFieldCodec<P3, R> codec) {
            return new Part3<>(codec, this);
        }
    }

    public static class Part3<P1, P2, P3, R> extends Part<P3, R, Function3<P1, P2, P3, R>, Part2<P1, P2, R>> {

        Part3(BoundFieldCodec<P3, R> codec, Part2<P1, P2, R> child) {
            super(codec, child);
        }

        @Override
        public DataResult<R> apply(Function3<P1, P2, P3, R> func3) {
            return this.child.apply(func3.bind(this.result.result(System.out::println)));
        }
    }

}
