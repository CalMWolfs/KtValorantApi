package com.calmwolfs.ktvalorantapi.models

import com.calmwolfs.valorantmodelapi.enums.CompetitiveRankType
import com.calmwolfs.valorantmodelapi.enums.SeasonType
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class StoredMmrHistory(
    @SerializedName("match_id") val matchId: String,
    @SerializedName("tier") val rank: CompetitiveRankType,
    val map: MapInfo,
    val season: SeasonType,
    @SerializedName("ranking_in_tier") val rankingInTier: Int,
    @SerializedName("last_mmr_change") val lastMmrChange: Int,
    val elo: Int,
    val date: Long,
)