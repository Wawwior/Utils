package me.wawwior.utils.serialization;

import com.google.gson.JsonObject;

public interface MapDecoder<T> {

    DataResult<T> decodeFrom(JsonObject jsonObject);

}
