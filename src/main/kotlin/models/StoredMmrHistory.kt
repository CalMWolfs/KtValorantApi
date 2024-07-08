package models

import com.calmwolfs.valorantmodelapi.enums.CompetitiveRankType
import com.calmwolfs.valorantmodelapi.enums.SeasonType
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class StoredMmrHistory(
    @Expose @SerializedName("match_id") val matchId: String,
    @Expose @SerializedName("tier") val rank: CompetitiveRankType,
    @Expose val map: MapInfo,
    @Expose val season: SeasonType,
    @Expose @SerializedName("ranking_in_tier") val rankingInTier: Int,
    @Expose @SerializedName("last_mmr_change") val lastMmrChange: Int,
    @Expose val elo: Int,
    @Expose val date: Long,
)