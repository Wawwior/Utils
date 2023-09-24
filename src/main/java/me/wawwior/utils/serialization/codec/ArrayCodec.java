package me.wawwior.utils.serialization.codec;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import me.wawwior.utils.serialization.Codec;
import me.wawwior.utils.serialization.DataResult;

import java.util.function.Function;

public class ArrayCodec<T> implements Codec<T[]> {

    private final Codec<T> elementCodec;
    private final Function<Integer, T[]> arrayConstructor;

    public ArrayCodec(Codec<T> elementCodec, Function<Integer, T[]> arrayConstructor) {
        this.elementCodec = elementCodec;
        this.arrayConstructor = arrayConstructor;
    }

    @Override
    public DataResult<T[]> decode(JsonElement element) {
        if (!element.isJsonArray()) return DataResult.error("Not a JSON array");
        JsonArray jsonArray = element.getAsJsonArray();
        T[] array = arrayConstructor.apply(jsonArray.size());
        for (int i = 0; i < jsonArray.size(); i++) {
            DataResult<T> result = elementCodec.decode(jsonArray.get(i));
            if (result.error().isPresent()) return DataResult.partial(array, result.error().get());
            array[i] = result.result(s -> { throw new IllegalStateException("Unexpected error"); });
        }
        return DataResult.success(array);
    }

    @Override
    public JsonElement encode(T[] object) {
        JsonArray jsonArray = new JsonArray();
        for (T t : object) {
            jsonArray.add(elementCodec.encode(t));
        }
        return jsonArray;
    }
}
