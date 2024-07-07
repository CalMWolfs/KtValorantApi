package enums

import com.google.gson.TypeAdapter
import com.google.gson.stream.JsonReader
import com.google.gson.stream.JsonToken
import com.google.gson.stream.JsonWriter

// todo work out ids
enum class Season(val shortName: String, val displayName: String, val id: String) {
    EPISODE_1_ACT_1("e1a1", "Episode 1 Act 1", "e1a1"),
    EPISODE_1_ACT_2("e1a2", "Episode 1 Act 2", "e1a2"),
    EPISODE_1_ACT_3("e1a3", "Episode 1 Act 3", "e1a3"),
    EPISODE_2_ACT_1("e2a1", "Episode 2 Act 1", "e2a1"),
    EPISODE_2_ACT_2("e2a2", "Episode 2 Act 2", "e2a2"),
    EPISODE_2_ACT_3("e2a3", "Episode 2 Act 3", "e2a3"),
    EPISODE_3_ACT_1("e3a1", "Episode 3 Act 1", "e3a1"),
    EPISODE_3_ACT_2("e3a2", "Episode 3 Act 2", "e3a2"),
    EPISODE_3_ACT_3("e3a3", "Episode 3 Act 3", "e3a3"),
    EPISODE_4_ACT_1("e4a1", "Episode 4 Act 1", "e4a1"),
    EPISODE_4_ACT_2("e4a2", "Episode 4 Act 2", "e4a2"),
    EPISODE_4_ACT_3("e4a3", "Episode 4 Act 3", "e4a3"),
    EPISODE_5_ACT_1("e5a1", "Episode 5 Act 1", "e5a1"),
    EPISODE_5_ACT_2("e5a2", "Episode 5 Act 2", "e5a2"),
    EPISODE_5_ACT_3("e5a3", "Episode 5 Act 3", "e5a3"),
    EPISODE_6_ACT_1("e6a1", "Episode 6 Act 1", "e6a1"),
    EPISODE_6_ACT_2("e6a2", "Episode 6 Act 2", "e6a2"),
    EPISODE_6_ACT_3("e6a3", "Episode 6 Act 3", "e6a3"),
    EPISODE_7_ACT_1("e7a1", "Episode 7 Act 1", "e7a1"),
    EPISODE_7_ACT_2("e7a2", "Episode 7 Act 2", "e7a2"),
    EPISODE_7_ACT_3("e7a3", "Episode 7 Act 3", "e7a3"),
    EPISODE_8_ACT_1("e8a1", "Episode 8 Act 1", "e8a1"),
    EPISODE_8_ACT_2("e8a2", "Episode 8 Act 2", "e8a2"),
    EPISODE_8_ACT_3("e8a3", "Episode 8 Act 3", "e8a3"),
    EPISODE_9_ACT_1("e9a1", "Episode 9 Act 1", "52ca6698-41c1-e7de-4008-8994d2221209"),
    ;

    override fun toString(): String {
        return displayName
    }

    companion object {
        fun fromShortName(shortName: String): Season {
            return entries.first { it.shortName == shortName }
        }

        // todo make this work with id not short name maybe?
        val typeAdapter: TypeAdapter<Season> = object : TypeAdapter<Season>() {
            override fun write(writer: JsonWriter, value: Season) {
                writer.name("short").value(value.shortName)
            }

            override fun read(reader: JsonReader): Season {
                var shortName = "e1a1"
                val token = reader.peek()
                if (token == JsonToken.NUMBER) {
                    shortName = reader.nextString()
                } else if (token == JsonToken.BEGIN_OBJECT) {
                    reader.beginObject()
                    while (reader.hasNext()) {
                        when (reader.nextName()) {
                            "short" -> shortName = reader.nextString()
                            else -> reader.skipValue()
                        }
                    }
                    reader.endObject()
                }
                return fromShortName(shortName)
            }
        }
    }
}