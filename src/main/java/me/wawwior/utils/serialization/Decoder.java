package me.wawwior.utils.serialization;

import com.google.gson.JsonElement;

public interface Decoder<T> {

    DataResult<T> decode(JsonElement element);

}
