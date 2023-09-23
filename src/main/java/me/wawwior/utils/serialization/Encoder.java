package me.wawwior.utils.serialization;

import com.google.gson.JsonElement;

public interface Encoder<T> {

    JsonElement encode(T object);

}
