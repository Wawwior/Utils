package me.wawwior.utils.serialization.codec;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import me.wawwior.utils.serialization.Codec;
import me.wawwior.utils.serialization.DataResult;

import java.util.ArrayList;
import java.util.List;

public class ListCodec<T> implements Codec<List<T>> {

    private final Codec<T> elementCodec;

    public ListCodec(Codec<T> elementCodec) {
        this.elementCodec = elementCodec;
    }

    @Override
    public JsonElement encode(List<T> object) {
        JsonArray jsonArray = new JsonArray();
        for (T t : object) {
            jsonArray.add(elementCodec.encode(t));
        }
        return jsonArray;
    }

    @Override
    public DataResult<List<T>> decode(JsonElement element) {
        if (!element.isJsonArray()) return DataResult.error("Not a JSON array");
        JsonArray jsonArray = element.getAsJsonArray();
        List<T> list = new ArrayList<>();
        for (JsonElement jsonElement : jsonArray) {
            DataResult<T> result = elementCodec.decode(jsonElement);
            if (result.error().isPresent()) return DataResult.partial(list, result.error().get());
            list.add(result.result(s -> { throw new IllegalStateException("Unexpected error"); }));
        }
        return DataResult.success(list);
    }
}
