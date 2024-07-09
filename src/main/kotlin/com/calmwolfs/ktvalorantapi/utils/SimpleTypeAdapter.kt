package com.calmwolfs.ktvalorantapi.utils

import com.google.gson.TypeAdapter
import com.google.gson.stream.JsonReader
import com.google.gson.stream.JsonWriter

class SimpleTypeAdapter<T>(
    val serializer: T.() -> String,
    val deserializer: JsonReader.() -> T
) : TypeAdapter<T>() {
    override fun write(writer: JsonWriter, value: T) {
        writer.value(value.serializer())
    }

    override fun read(reader: JsonReader): T {
        return deserializer(reader)
    }
}