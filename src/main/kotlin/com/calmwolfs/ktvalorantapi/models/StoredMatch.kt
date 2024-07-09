package com.calmwolfs.ktvalorantapi.models

import com.calmwolfs.ktvalorantapi.enums.Region
import com.calmwolfs.valorantmodelapi.enums.AgentType
import com.calmwolfs.valorantmodelapi.enums.CompetitiveRankType
import com.calmwolfs.valorantmodelapi.enums.MapType
import com.calmwolfs.valorantmodelapi.enums.SeasonType
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.util.Date

data class StoredMatch(
    val meta: MapMetadata,
    val stats: MatchStats,
    val teams: TeamsInfo,
)

data class MapMetadata(
    val id: String,
    val map: MapType,
    val version: String,
    // todo enum for modes
    val mode: String,
    @SerializedName("started_at") val startedAt: Date,
    val season: SeasonType,
    val region: Region,
    val cluster: String,
)

data class MatchStats(
    @SerializedName("puuid") val playerId: String,
    val team: String,
    val level: Int,
    val character: AgentType,
    @SerializedName("tier") val rank: CompetitiveRankType,
    val score: Int,
    val kills: Int,
    val deaths: Int,
    @Expose val assists: Int,
    @Expose val shots: ShotInfo,
    @Expose val damage: DamageInfo,
)

data class ShotInfo(
    @Expose val head: Int,
    @Expose val body: Int,
    @Expose val leg: Int,
)

data class DamageInfo(
    @Expose val made: Int,
    @Expose val received: Int,
)

// todo does this break in death match?
data class TeamsInfo(
    @Expose val red: Int,
    @Expose val blue: Int,
)