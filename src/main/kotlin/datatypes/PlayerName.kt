package datatypes

import com.google.gson.TypeAdapter
import com.google.gson.stream.JsonReader
import com.google.gson.stream.JsonWriter

data class PlayerName(val name: String, val tag: String) {
    constructor(fullName: String) : this(
        fullName.substringBefore("#").also {
            if (!fullName.contains("#")) {
                throw IllegalArgumentException("Name must contain a '#' character")
            }
        },
        fullName.substringAfter("#")
    )

    val params = mutableMapOf("name" to name, "tag" to tag)
    val displayName = "$name#$tag"

    override fun toString(): String {
        return displayName
    }
    companion object {
        val typeAdapter: TypeAdapter<PlayerName> = object : TypeAdapter<PlayerName>() {
            override fun write(writer: JsonWriter, value: PlayerName) {
                writer.value(value.displayName)
            }

            override fun read(reader: JsonReader): PlayerName {
                return PlayerName(reader.nextString())
            }
        }
    }
}
