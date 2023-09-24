package me.wawwior.utils.serialization;

import com.google.gson.JsonObject;

public interface FieldDecoder<T> {

    DataResult<T> decodeFrom(JsonObject jsonObject);

}
