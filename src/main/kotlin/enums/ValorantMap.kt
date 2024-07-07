package enums

import com.google.gson.TypeAdapter
import com.google.gson.stream.JsonReader
import com.google.gson.stream.JsonWriter

enum class ValorantMap(val mapName: String, mapId: String) {
    ASCENT("Ascent", "7eaecc1b-4337-bbf6-6ab9-04b8f06b3319"),
    BIND("Bind", "2c9d57ec-4431-9c5e-2939-8f9ef6dd5cba"),
    BREEZE("Breeze", "2fb9a4fd-47b8-4e7d-a969-74b4046ebd53"),
    FRACTURE("Fracture", "b529448b-4d60-346e-e89e-00a4c527a405"),
    HAVEN("Haven", "2bee0dc9-4ffe-519b-1cbd-7fbe763a6047"),
    ICEBOX("Icebox", "e2ad5c54-4114-a870-9641-8ea21279579a"),
    SPLIT("Split", "d960549e-485c-e861-8d71-aa9d1aed12a2"),
    DISTRICT("District", ""),
    KABASH("Kabash", ""),
    PIAZZA("Piazza", ""),
    LOTUS("Lotus", ""),
    PEARL("Pearl", ""),
    ;

    override fun toString(): String {
        return mapName
    }

    companion object {
        fun fromSerializedName(serializedName: String): ValorantMap {
            return entries.first { it.mapName.lowercase() == serializedName.lowercase() }
        }

        val typeAdapter: TypeAdapter<ValorantMap> = object : TypeAdapter<ValorantMap>() {
            override fun write(writer: JsonWriter, value: ValorantMap) {
                writer.value(value.mapName)
            }

            override fun read(reader: JsonReader): ValorantMap {
                return fromSerializedName(reader.nextString())
            }
        }
    }
}