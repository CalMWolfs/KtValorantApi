package com.calmwolfs.ktvalorantapi.models

import com.calmwolfs.valorantmodelapi.enums.CompetitiveRankType
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Leaderboard(
    @SerializedName("updated_at") val updatedAt: Long,
    val thresholds: List<RankThreshold>,
    val players: List<LeaderboardPlayer>,
)

data class RankThreshold(
    @SerializedName("tier") val rank: CompetitiveRankType,
    @SerializedName("start_index") val startIndex: Int,
    val threshold: Int,
)

data class LeaderboardPlayer(
    @SerializedName("puuid") val playerId: String,
    val name: String,
    val tag: String,
    @Expose val card: String,
    @Expose val title: String,
    @Expose @SerializedName("is_banned") val isBanned: Boolean,
    @Expose @SerializedName("is_anonymized") val isAnonymized: Boolean,
    @Expose @SerializedName("leaderboard_rank") val leaderboardRank: Int,
    @Expose @SerializedName("rr") val rankedRating: Int,
    @Expose val wins: Int,
    @Expose @SerializedName("updated_at") val updatedAt: Long,
    @Expose @SerializedName("tier") val rank: CompetitiveRankType,
)
