package me.wawwior.utils.serialization;

import com.google.gson.JsonElement;
import com.google.gson.JsonNull;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import me.wawwior.utils.serialization.codec.ListCodec;
import me.wawwior.utils.serialization.codec.MapCodec;

import java.util.function.Function;

public interface Codec<T> extends Encoder<T>, Decoder<T> {

    // !!! Primitive Wrappers should be not null
    Codec<Integer> INT = Codec.of(
            object -> {
                if (object == null) return JsonNull.INSTANCE;
                return new JsonPrimitive(object);
            },
            element -> {
                if (!element.isJsonPrimitive()) return DataResult.error("Not a JSON primitive");
                JsonPrimitive jsonPrimitive = element.getAsJsonPrimitive();
                if (!jsonPrimitive.isNumber()) return DataResult.error("Not a JSON number");
                return DataResult.success(jsonPrimitive.getAsInt());
            }
    );

    static <T> Codec<T> of(Encoder<T> encoder, Decoder<T> decoder) {
        return new Codec<>() {

            @Override
            public JsonElement encode(T object) {
                return encoder.encode(object);
            }

            @Override
            public DataResult<T> decode(JsonElement element) {
                return decoder.decode(element);
            }
        };
    }

    default ListCodec<T> list() {
        return new ListCodec<>(this);
    }

    default MapCodec<T> map() {
        return new MapCodec<>(this);
    }

    default Codec<T> field(String field) {
        return new Codec<>() {
            @Override
            public DataResult<T> decode(JsonElement element) {
                return element.isJsonObject() ? decode(element.getAsJsonObject().get(field)) : DataResult.error("Not a JSON object");
            }

            @Override
            public JsonElement encode(T object) {
                JsonObject jsonObject = new JsonObject();
                jsonObject.add(field, Codec.this.encode(object));
                return jsonObject;
            }
        };
    }

    static <T> Codec<T> mapped(Function<CodecMapper<T>, Codec<T>> mapper) {
        return mapper.apply(new CodecMapper<>());
    }

}
