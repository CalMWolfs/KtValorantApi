package models

import com.calmwolfs.valorantmodelapi.enums.CompetitiveRankType
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class MmrHistory(
    @Expose @SerializedName("currenttier") val currentTier: CompetitiveRankType,
    @Expose @SerializedName("match_id") val matchId: String,
    @Expose val map: MapInfo,
    @Expose @SerializedName("season_id") val seasonId: String,
    @Expose @SerializedName("ranking_in_tier") val rankingInTier: Int,
    @Expose @SerializedName("mmr_change_to_last_game") val mmrChangeToLastGame: Int,
    @Expose val elo: Int,
    @Expose @SerializedName("date_raw") val date: Long,
)
