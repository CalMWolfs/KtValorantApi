package com.calmwolfs.ktvalorantapi.enums

import com.google.gson.TypeAdapter
import com.google.gson.stream.JsonReader
import com.google.gson.stream.JsonWriter

enum class Platform(val serializedName: String, val prettyName: String) {
    PC("pc", "Computer"),
    CONSOLE("console", "Console"),
    ;

    override fun toString(): String {
        return prettyName
    }

    companion object {
        fun fromSerializedName(serializedName: String): Platform {
            return entries.first { it.serializedName == serializedName.lowercase() }
        }

        val typeAdapter: TypeAdapter<Platform> = object : TypeAdapter<Platform>() {
            override fun write(writer: JsonWriter, value: Platform) {
                writer.value(value.serializedName)
            }

            override fun read(reader: JsonReader): Platform {
                return fromSerializedName(reader.nextString())
            }
        }
    }
}