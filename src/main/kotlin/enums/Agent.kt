package enums

import com.google.gson.TypeAdapter
import com.google.gson.stream.JsonReader
import com.google.gson.stream.JsonWriter

enum class Agent(val agentName: String, val id: String) {
    BREACH("Breach", ""),
    BRIMSTONE("Brimstone", ""),
    CYPER("Cypher", ""),
    JETT("Jett", ""),
    KAYO("KAY/O", ""),
    KILLJOY("Killjoy", ""),
    OMAN("Oman", ""),
    PHOENIX("Phoenix", ""),
    RAZE("Raze", ""),
    REYNA("Reyna", ""),
    SAGE("Sage", ""),
    SKYE("Skye", ""),
    SOVA("Sova", ""),
    VIPER("Viper", ""),
    YORU("Yoru", ""),
    ASTRA("Astra", ""),
    CHAMBER("Chamber", ""),
    NEON("Neon", ""),
    FADE("Fade", ""),
    HARBOR("Harbor", ""),
    GEKKO("Gekko", ""),
    DEADLOCK("Deadlock", ""),
    ISO("Iso", ""),
    CLOVE("Clove", ""),
    ;

    override fun toString(): String {
        return agentName
    }

    companion object {
        fun fromSerializedName(serializedName: String): Agent {
            return entries.first { it.agentName == serializedName.lowercase() }
        }

        val typeAdapter: TypeAdapter<Agent> = object : TypeAdapter<Agent>() {
            override fun write(writer: JsonWriter, value: Agent) {
                writer.value(value.agentName)
            }

            override fun read(reader: JsonReader): Agent {
                return fromSerializedName(reader.nextString())
            }
        }
    }
}