package me.wawwior.utils.serialization;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import me.wawwior.utils.common.functions.Function2;
import me.wawwior.utils.common.functions.Function3;
import me.wawwior.utils.common.functions.Function4;

import java.util.function.Function;

public class CodecMapper<R> {

    public <P1> Group1<P1, R> bind(String name, Function<R, P1> getter, Codec<P1> codec) {
        return new Group1<>(name, getter, codec);
    }

    static abstract class Group<P1, R, F, C extends Group> implements Function<F, DataResult<R>> {

        private final String key;
        private final Function<R, P1> getter;
        private final Codec<P1> codec;
        protected final C child;
        protected DataResult<P1> result;

        Group(String key, Function<R, P1> getter, Codec<P1> codec, C child) {
            this.key = key;
            this.getter = getter;
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
            jsonObject.add(key, codec.encode(getter.apply(object)));
        }

        public void decodeFrom(JsonObject jsonObject) {
            if (child != null) child.decodeFrom(jsonObject);
            if (!jsonObject.has(key)) {
                result = DataResult.error("Missing key: " + key);
            }
            result = codec.decode(jsonObject.get(key));
        }

    }

    static class Group1<P1, R> extends Group<P1, R, Function<P1, R>, Group> {

        Group1(String key, Function<R, P1> getter, Codec<P1> codec) {
            super(key, getter, codec, null);
        }

        @Override
        public DataResult<R> apply(Function<P1, R> p1RFunction) {
            return this.result.map(p1RFunction);
        }

        public <P2> Group2<P1, P2, R> bind(String name, Function<R, P2> getter, Codec<P2> codec) {
            return new Group2<>(name, getter, codec, this);
        }

    }

    static class Group2<P1, P2, R> extends Group<P2, R, Function2<P1, P2, R>, Group1<P1, R>> {

        Group2(String key, Function<R, P2> getter, Codec<P2> codec, Group1<P1, R> child) {
            super(key, getter, codec, child);
        }

        @Override
        public DataResult<R> apply(Function2<P1, P2, R> p1P2RFunction2) {
            return this.child.apply(p1P2RFunction2.bind(this.result.result(System.out::println)));
        }

        public <P3> Group3<P1, P2, P3, R> bind(String name, Function<R, P3> getter, Codec<P3> codec) {
            return new Group3<>(name, getter, codec, this);
        }
    }

    static class Group3<P1, P2, P3, R> extends Group<P3, R, Function3<P1, P2, P3, R>, Group2<P1, P2, R>> {

        Group3(String key, Function<R, P3> getter, Codec<P3> codec, Group2<P1, P2, R> child) {
            super(key, getter, codec, child);
        }

        @Override
        public DataResult<R> apply(Function3<P1, P2, P3, R> p1P2P3RFunction3) {
            return this.child.apply(p1P2P3RFunction3.bind(this.result.result(System.out::println)));
        }

        public <P4> Group4<P1, P2, P3, P4, R> bind(String name, Function<R, P4> getter, Codec<P4> codec) {
            return new Group4<>(name, getter, codec, this);
        }
    }

    static class Group4<P1, P2, P3, P4, R> extends Group<P4, R, Function4<P1, P2, P3, P4, R>, Group3<P1, P2, P3, R>> {

        Group4(String key, Function<R, P4> getter, Codec<P4> codec, Group3<P1, P2, P3, R> child) {
            super(key, getter, codec, child);
        }

        @Override
        public DataResult<R> apply(Function4<P1, P2, P3, P4, R> p1P2P3P4RFunction4) {
            return this.child.apply(p1P2P3P4RFunction4.bind(this.result.result(System.out::println)));
        }
    }

}
