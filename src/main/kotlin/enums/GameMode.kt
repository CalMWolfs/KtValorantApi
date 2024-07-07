package enums

import com.google.gson.TypeAdapter
import com.google.gson.stream.JsonReader
import com.google.gson.stream.JsonWriter

enum class GameMode(val modeName: String, val apiName: String, val id: String) {
    COMPETITIVE("Competitive", "competitive", ""),
    DEATHMATCH("Deathmatch", "deathmatch", ""),
    CUSTOM("Custom", "custom", ""),
    SPIKE_RUSH("Spike Rush", "spikerush", ""),
    UNRATED("Unrated", "unrated", ""),
    TEAM_DEATHMATCH("Team Deathmatch", "teamdeathmatch", ""),
    SNOWBALL_FIGHT("Snowball Fight", "snowballfight", ""),
    NEW_MAP("New Map", "newmap", ""),
    REPLICATION("Replication", "replication", ""),
    ESCALATION("Escalation", "escalation", ""),
    SWIFTPLAY("Swiftplay", "swiftplay", ""),
    ;

    override fun toString(): String {
        return modeName
    }

    companion object {
        fun fromSerializedName(serializedName: String): GameMode {
            return entries.first { it.modeName == serializedName.lowercase() }
        }

        val typeAdapter: TypeAdapter<GameMode> = object : TypeAdapter<GameMode>() {
            override fun write(writer: JsonWriter, value: GameMode) {
                writer.value(value.modeName)
            }

            override fun read(reader: JsonReader): GameMode {
                return fromSerializedName(reader.nextString())
            }
        }
    }
}
