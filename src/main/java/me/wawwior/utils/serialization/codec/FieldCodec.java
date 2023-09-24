package me.wawwior.utils.serialization.codec;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import me.wawwior.utils.serialization.Codec;
import me.wawwior.utils.serialization.DataResult;
import me.wawwior.utils.serialization.MapDecoder;
import me.wawwior.utils.serialization.MapEncoder;

import java.util.function.Function;

public class FieldCodec<T> implements MapEncoder<T>, MapDecoder<T> {

    private final String fieldName;
    private final Codec<T> elementCodec;

    public FieldCodec(String fieldName, Codec<T> elementCodec) {
        this.fieldName = fieldName;
        this.elementCodec = elementCodec;
    }


    @Override
    public void encodeTo(T object, JsonObject jsonObject) {
        JsonElement element = elementCodec.encode(object);
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

   public  <R> BoundFieldCodec<T, R> bind(Function<R, T> getter) {
        return new BoundFieldCodec<>(fieldName, elementCodec, getter);
    }

}
