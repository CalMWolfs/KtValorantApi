package models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import enums.Rank

data class Leaderboard(
    @Expose @SerializedName("updated_at") val updatedAt: Long,
    @Expose val thresholds: List<RankThreshold>,
    @Expose val players: List<LeaderboardPlayer>,
)

data class RankThreshold(
    @Expose @SerializedName("tier") val rank: Rank,
    @Expose @SerializedName("start_index") val startIndex: Int,
    @Expose val threshold: Int,
)

data class LeaderboardPlayer(
    @Expose @SerializedName("puuid") val playerId: String,
    @Expose val name: String,
    @Expose val tag: String,
    @Expose val card: String,
    @Expose val title: String,
    @Expose @SerializedName("is_banned") val isBanned: Boolean,
    @Expose @SerializedName("is_anonymized") val isAnonymized: Boolean,
    @Expose @SerializedName("leaderboard_rank") val leaderboardRank: Int,
    @Expose @SerializedName("rr") val rankedRating: Int,
    @Expose val wins: Int,
    @Expose @SerializedName("updated_at") val updatedAt: Long,
    @Expose @SerializedName("tier") val rank: Rank,
)
