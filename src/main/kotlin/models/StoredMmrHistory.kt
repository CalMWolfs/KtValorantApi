package models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import enums.Rank
import enums.Season

data class StoredMmrHistory(
    @Expose @SerializedName("match_id") val matchId: String,
    @Expose @SerializedName("tier") val rank: Rank,
    // todo enum for maps
    @Expose val map: MapInfo,
    @Expose val season: Season,
    @Expose @SerializedName("ranking_in_tier") val rankingInTier: Int,
    @Expose @SerializedName("last_mmr_change") val lastMmrChange: Int,
    @Expose val elo: Int,
    @Expose val date: Long,
)