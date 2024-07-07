package utils

import datatypes.PlayerName
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.TypeAdapter
import com.google.gson.stream.JsonReader
import com.google.gson.stream.JsonToken
import com.google.gson.stream.JsonWriter
import enums.Agent
import enums.GameMode
import enums.Platform
import enums.Rank
import enums.Region
import enums.Season
import enums.ValorantMap
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter

object GsonUtils {

    fun gson(): Gson = GsonBuilder().setPrettyPrinting()
        .excludeFieldsWithoutExposeAnnotation()
        .registerTypeAdapter(Region::class.java, Region.typeAdapter.nullSafe())
        .registerTypeAdapter(Platform::class.java, Platform.typeAdapter.nullSafe())
        .registerTypeAdapter(Rank::class.java, Rank.typeAdapter.nullSafe())
        .registerTypeAdapter(ValorantMap::class.java, ValorantMap.typeAdapter.nullSafe())
        .registerTypeAdapter(Season::class.java, Season.typeAdapter.nullSafe())
        .registerTypeAdapter(Agent::class.java, Agent.typeAdapter.nullSafe())
        .registerTypeAdapter(PlayerName::class.java, PlayerName.typeAdapter.nullSafe())
        .registerTypeAdapter(GameMode::class.java, GameMode.typeAdapter.nullSafe())
        .registerTypeAdapter(Long::class.java, ZonedDateTimeAdapter().nullSafe())
        .create()

    private class ZonedDateTimeAdapter : TypeAdapter<Long>() {
        private val formatter = DateTimeFormatter.ISO_DATE_TIME

        override fun write(writer: JsonWriter, value: Long) {
            writer.value(value)
        }

        override fun read(reader: JsonReader): Long {
            val token = reader.peek()
            if (token == JsonToken.NUMBER) {
                return reader.nextLong()
            }

            val dateTime = ZonedDateTime.parse(reader.nextString(), formatter)
            return dateTime.toInstant().toEpochMilli()
        }
    }
}