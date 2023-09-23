package me.wawwior.utils.serialization.codec;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import me.wawwior.utils.common.StringMap;
import me.wawwior.utils.serialization.Codec;
import me.wawwior.utils.serialization.DataResult;

import java.util.Map;

public class MapCodec<T> implements Codec<StringMap<T>> {

    private final Codec<T> elementCodec;

    public MapCodec(Codec<T> elementCodec) {
        this.elementCodec = elementCodec;
    }

    @Override
    public JsonElement encode(StringMap<T> object) {
        JsonObject jsonObject = new JsonObject();
        object.forEach((key, value) -> jsonObject.add(key, elementCodec.encode(value)));
        return jsonObject;
    }

    @Override
    public DataResult<StringMap<T>> decode(JsonElement element) {
        if (!element.isJsonObject()) return DataResult.error("Not a JSON object");
        JsonObject jsonObject = element.getAsJsonObject();
        StringMap<T> map = new StringMap<>();
        for (Map.Entry<String, JsonElement> entry : jsonObject.entrySet()) {
            DataResult<T> result = elementCodec.decode(entry.getValue());
            if (result.error().isPresent()) return DataResult.partial(map, result.error().get());
            map.put(entry.getKey(), result.result(s -> { throw new IllegalStateException("Unexpected error"); }));
        }
        return DataResult.success(map);
    }

}
