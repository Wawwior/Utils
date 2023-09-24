package me.wawwior.utils.serialization;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

public interface FieldEncoder<T> {

    void encodeTo(T object, JsonObject jsonObject);

}
