package com.calmwolfs.ktvalorantapi.utils

import com.calmwolfs.ktvalorantapi.datatypes.PlayerName
import com.calmwolfs.ktvalorantapi.enums.Language
import com.calmwolfs.ktvalorantapi.enums.Platform
import com.calmwolfs.ktvalorantapi.enums.Region
import com.calmwolfs.valorantmodelapi.enums.AgentType
import com.calmwolfs.valorantmodelapi.enums.CompetitiveRankType
import com.calmwolfs.valorantmodelapi.enums.GamemodeType
import com.calmwolfs.valorantmodelapi.enums.GearType
import com.calmwolfs.valorantmodelapi.enums.LevelBorderType
import com.calmwolfs.valorantmodelapi.enums.MapType
import com.calmwolfs.valorantmodelapi.enums.PlayerCardType
import com.calmwolfs.valorantmodelapi.enums.PlayerTitleType
import com.calmwolfs.valorantmodelapi.enums.SeasonType
import com.calmwolfs.valorantmodelapi.enums.WeaponType
import com.google.gson.TypeAdapter
import com.google.gson.stream.JsonReader
import com.google.gson.stream.JsonToken
import com.google.gson.stream.JsonWriter

object TypeAdapters {

    fun GamemodeType.apiName() = name.lowercase().replace("_", "")

    val agentTypeAdapter = SimpleTypeAdapter(AgentType::uuid) { AgentType.fromId(getId(this)) }
    val gamemodeTypeAdapter = SimpleTypeAdapter(GamemodeType::uuid) { GamemodeType.fromId(getId(this)) }
    val seasonTypeAdapter = SimpleTypeAdapter(SeasonType::uuid) { SeasonType.fromId(getId(this)) }
    val mapTypeAdapter = SimpleTypeAdapter(MapType::uuid) { MapType.fromId(getId(this)) }
    val weaponTypeAdapter = SimpleTypeAdapter(WeaponType::uuid) { WeaponType.fromId(getId(this)) }
    val gearTypeAdapter = SimpleTypeAdapter(GearType::uuid) { GearType.fromId(getId(this)) }
    val playerCardTypeAdapter = SimpleTypeAdapter(PlayerCardType::uuid) { PlayerCardType.fromId(getId(this)) }
    val playerTitleTypeAdapter = SimpleTypeAdapter(PlayerTitleType::uuid) { PlayerTitleType.fromId(getId(this)) }
    val levelBorderTypeAdapter = SimpleTypeAdapter(LevelBorderType::uuid) { LevelBorderType.fromId(getId(this)) }
    val playerNameTypeAdapter = SimpleTypeAdapter(PlayerName::displayName) { PlayerName(this.nextString()) }
    val languageTypeAdapter = SimpleTypeAdapter(Language::localeUrl) { Language.fromLocaleUrl(this.nextString()) }
    val regionTypeAdapter = SimpleTypeAdapter(Region::apiName) { Region.fromSerializedName(this.nextString()) }
    val platformTypeAdapter = SimpleTypeAdapter(Platform::apiName) { Platform.fromSerializedName(this.nextString()) }

    private fun getId(reader: JsonReader): String? {
        val token = reader.peek()
        var id: String? = null
        when (token) {
            JsonToken.BEGIN_OBJECT -> {
                reader.beginObject()
                while (reader.hasNext()) {
                    when (reader.nextName()) {
                        "id" -> id = reader.nextString()
                        else -> reader.skipValue()
                    }
                }
                reader.endObject()
            }

            JsonToken.STRING -> id = reader.nextString()
            else -> return null
        }
        return id
    }

    val competitiveRankTypeAdapter = object : TypeAdapter<CompetitiveRankType>() {
        override fun write(writer: JsonWriter, value: CompetitiveRankType) {
            writer.value(value.tier)
        }

        override fun read(reader: JsonReader): CompetitiveRankType? {
            val token = reader.peek()
            var id: Int? = null
            when (token) {
                JsonToken.BEGIN_OBJECT -> {
                    reader.beginObject()
                    while (reader.hasNext()) {
                        when (reader.nextName()) {
                            "tier" -> id = reader.nextInt()
                            "id" -> id = reader.nextInt()
                            else -> reader.skipValue()
                        }
                    }
                    reader.endObject()
                }

                JsonToken.NUMBER -> id = reader.nextInt()
                else -> return null
            }
            return CompetitiveRankType.fromTier(id)
        }
    }
}
