package models

import com.calmwolfs.valorantmodelapi.enums.AgentType
import com.calmwolfs.valorantmodelapi.enums.CompetitiveRankType
import com.calmwolfs.valorantmodelapi.enums.SeasonType
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import enums.Region

data class StoredMatch(
    @Expose val meta: MapMetadata,
    @Expose val stats: MatchStats,
    @Expose val teams: TeamsInfo,
)

data class MapMetadata(
    @Expose val id: String,
    @Expose val map: MapInfo,
    @Expose val version: String,
    // todo enum for modes
    @Expose val mode: String,
    @Expose @SerializedName("started_at") val startedAt: Long,
    @Expose val season: SeasonType,
    @Expose val region: Region,
    @Expose val cluster: String,
)

data class MapInfo(
    @Expose val id: String,
    @Expose val name: String,
)

data class MatchStats(
    @Expose @SerializedName("puuid") val playerId: String,
    @Expose val team: String,
    @Expose val level: Int,
    @Expose val character: AgentType,
    @Expose @SerializedName("tier") val rank: CompetitiveRankType,
    @Expose val score: Int,
    @Expose val kills: Int,
    @Expose val deaths: Int,
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