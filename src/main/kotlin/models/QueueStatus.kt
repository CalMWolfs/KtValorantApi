package models

import com.calmwolfs.valorantmodelapi.enums.CompetitiveRankType
import com.calmwolfs.valorantmodelapi.enums.MapType
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import enums.Platform

data class QueueStatus(
    // todo use enum
    @Expose val mode: String,
    @Expose @SerializedName("mode_id") val modeId: String,
    @Expose val enabled: Boolean,
    @Expose @SerializedName("team_size") val teamSize: Int,
    @Expose @SerializedName("number_of_teams") val numberOfTeams: Int,
    @Expose @SerializedName("party_size") val partySize: PartySize,
    @Expose @SerializedName("high_skill") val highSkill: HighSkill,
    @Expose val ranked: Boolean,
    @Expose val tournament: Boolean,
    @Expose @SerializedName("skill_disparity") val skillDisparity: List<SkillDisparity>,
    @Expose @SerializedName("required_account_level") val requiredAccountLevel: Int,
    @Expose @SerializedName("game_rules") val gameRules: GameRules,
    @Expose val platforms: List<Platform>,
    @Expose val maps: List<QueueMapInfo>
)

data class PartySize(
    @Expose val min: Int,
    @Expose val max: Int,
    @Expose val invalid: List<Int>,
    @Expose @SerializedName("full_party_bypass") val fullPartyBypass: Boolean,
)

data class HighSkill(
    @Expose @SerializedName("max_party_size") val maxPartySize: Int,
    @Expose @SerializedName("min_tier") val minTier: Int,
    @Expose @SerializedName("max_tier") val maxTier: Int,
)

data class SkillDisparity(
    @Expose @SerializedName("tier") val rank: CompetitiveRankType,
    @Expose @SerializedName("max_tier") val maxRank: CompetitiveRankType
)

data class GameRules(
    @Expose @SerializedName("overtime_win_by_two") val overtimeWinByTwo: Boolean,
    @Expose @SerializedName("allow_lenient_surrender") val allowLenientSurrender: Boolean,
    @Expose @SerializedName("allow_drop_out") val allowDropOut: Boolean,
    @Expose @SerializedName("assign_random_agents") val assignRandomAgents: Boolean,
    @Expose @SerializedName("skip_pregame") val skipPregame: Boolean,
    @Expose @SerializedName("allow_overtime_draw_votes") val allowOvertimeDrawVotes: Boolean,
    @Expose @SerializedName("overtime_win_by_two_capped") val overtimeWinByTwoCapped: Boolean,
    @Expose @SerializedName("premier_mode") val premierMode: Boolean,
)

data class QueueMapInfo(
    @Expose val map: MapType,
    @Expose val enabled: Boolean,
)
