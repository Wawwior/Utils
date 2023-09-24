package me.wawwior.utils.serialization.codec;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import me.wawwior.utils.serialization.Codec;
import me.wawwior.utils.serialization.DataResult;
import me.wawwior.utils.serialization.MapDecoder;
import me.wawwior.utils.serialization.MapEncoder;

import java.util.function.Function;

public class BoundFieldCodec<T, R> implements MapEncoder<R>, MapDecoder<T> {

    private final String fieldName;
    private final Codec<T> elementCodec;
    private final Function<R, T> getter;

    public BoundFieldCodec(String fieldName, Codec<T> elementCodec, Function<R, T> getter) {
        this.fieldName = fieldName;
        this.elementCodec = elementCodec;
        this.getter = getter;
    }

    @Override
    public void encodeTo(R object, JsonObject jsonObject) {
        JsonElement element = elementCodec.encode(getter.apply(object));
        jsonObject.add(fieldName, element);
    }

    @Override
    public DataResult<T> decodeFrom(JsonObject jsonObject) {
        if (jsonObject.has(fieldName)) {
            JsonElement element = jsonObject.get(fieldName);
            return elementCodec.decode(element);
        }
        return DataResult.error("Missing field: " + fieldName);
    }
}
