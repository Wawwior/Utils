package me.wawwior.utils.serialization;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

public class FieldCodec<T> implements FieldEncoder<T>, FieldDecoder<T> {

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
}
