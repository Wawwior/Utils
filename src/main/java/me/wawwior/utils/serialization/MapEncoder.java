package me.wawwior.utils.serialization;

import com.google.gson.JsonObject;

public interface MapEncoder<T> {

    void encodeTo(T object, JsonObject jsonObject);

}
