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
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.JsonObject
import java.io.IOException
import java.nio.file.Files
import java.nio.file.Paths

object GsonUtils {

    val gson: Gson by lazy {
        GsonBuilder().setPrettyPrinting()
            .registerTypeAdapter(AgentType::class.java, TypeAdapters.agentTypeAdapter.nullSafe())
            .registerTypeAdapter(GamemodeType::class.java, TypeAdapters.gamemodeTypeAdapter.nullSafe())
            .registerTypeAdapter(CompetitiveRankType::class.java, TypeAdapters.competitiveRankTypeAdapter.nullSafe())
            .registerTypeAdapter(SeasonType::class.java, TypeAdapters.seasonTypeAdapter.nullSafe())
            .registerTypeAdapter(MapType::class.java, TypeAdapters.mapTypeAdapter.nullSafe())
            .registerTypeAdapter(WeaponType::class.java, TypeAdapters.weaponTypeAdapter.nullSafe())
            .registerTypeAdapter(GearType::class.java, TypeAdapters.gearTypeAdapter.nullSafe())
            .registerTypeAdapter(PlayerCardType::class.java, TypeAdapters.playerCardTypeAdapter.nullSafe())
            .registerTypeAdapter(PlayerTitleType::class.java, TypeAdapters.playerTitleTypeAdapter.nullSafe())
            .registerTypeAdapter(LevelBorderType::class.java, TypeAdapters.levelBorderTypeAdapter.nullSafe())
            .registerTypeAdapter(Region::class.java, TypeAdapters.regionTypeAdapter.nullSafe())
            .registerTypeAdapter(Platform::class.java, TypeAdapters.platformTypeAdapter.nullSafe())
            .registerTypeAdapter(PlayerName::class.java, TypeAdapters.playerNameTypeAdapter.nullSafe())
            .registerTypeAdapter(Language::class.java, TypeAdapters.languageTypeAdapter.nullSafe())
            .create()
    }

    fun getApiKey(): String {
        try {
            val content = String(Files.readAllBytes(Paths.get("secrets.json")))
            val secret = gson.fromJson(content, JsonObject::class.java)
            val key = secret.getAsJsonObject("secrets")["apiKey"].asString
                ?: throw IllegalArgumentException("API key not found in secrets.json")
            return key
        } catch (e: IOException) {
            throw IllegalArgumentException("secrets.json not found " + e.message)
        }
    }
}