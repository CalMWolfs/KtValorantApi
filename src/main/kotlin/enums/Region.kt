package enums

import com.google.gson.TypeAdapter
import com.google.gson.stream.JsonReader
import com.google.gson.stream.JsonWriter

enum class Region(val serializedName: String, val prettyName: String) {
    ASIA_PACIFIC("ap", "Asia-Pacific"),
    BRAZIL("br", "Brazil"),
    EUROPE("eu", "Europe"),
    KOREA("kr", "Korea"),
    LATIN_AMERICA("latam", "Latin America"),
    NORTH_AMERICA("na", "North America"),
    ;

    override fun toString(): String {
        return prettyName
    }

    companion object {
        fun fromSerializedName(serializedName: String): Region {
            return entries.first { it.serializedName == serializedName.lowercase() }
        }

        val typeAdapter: TypeAdapter<Region> = object : TypeAdapter<Region>() {
            override fun write(writer: JsonWriter, value: Region) {
                writer.value(value.serializedName)
            }

            override fun read(reader: JsonReader): Region {
                return fromSerializedName(reader.nextString())
            }
        }
    }
}