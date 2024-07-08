package com.calmwolfs.ktvalorantapi.models

import com.calmwolfs.valorantmodelapi.enums.CompetitiveRankType
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class MmrHistory(
    @SerializedName("currenttier") val currentTier: CompetitiveRankType,
    @SerializedName("match_id") val matchId: String,
    val map: MapInfo,
    @SerializedName("season_id") val seasonId: String,
    @SerializedName("ranking_in_tier") val rankingInTier: Int,
    @SerializedName("mmr_change_to_last_game") val mmrChangeToLastGame: Int,
    val elo: Int,
    @SerializedName("date_raw") val date: Long,
)
