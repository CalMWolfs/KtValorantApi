package com.calmwolfs.ktvalorantapi.models

import com.calmwolfs.ktvalorantapi.datatypes.Location
import com.calmwolfs.ktvalorantapi.enums.Platform
import com.calmwolfs.ktvalorantapi.enums.Region
import com.calmwolfs.valorantmodelapi.enums.AgentType
import com.calmwolfs.valorantmodelapi.enums.CompetitiveRankType
import com.calmwolfs.valorantmodelapi.enums.GearType
import com.calmwolfs.valorantmodelapi.enums.LevelBorderType
import com.calmwolfs.valorantmodelapi.enums.MapType
import com.calmwolfs.valorantmodelapi.enums.PlayerCardType
import com.calmwolfs.valorantmodelapi.enums.PlayerTitleType
import com.calmwolfs.valorantmodelapi.enums.SeasonType
import com.calmwolfs.valorantmodelapi.enums.WeaponType
import com.google.gson.JsonElement
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.util.Date

data class ValorantMatch(
    val metadata: GameMetadata,
    val players: List<PlayerInfo>,
    // todo work out what data structure these are
    val observers: List<JsonElement>,
    val coaches: List<JsonElement>,
    val teams: List<TeamInfo>,
    val rounds: List<GameRound>,
    val kills: List<KillEvent>,
)

data class GameMetadata(
    @SerializedName("match_id") val matchId: String,
    // todo change around
    val map: MapType,
    @SerializedName("game_version") val gameVersion: String,
    @SerializedName("game_length_in_ms") val gameLength: Int,
    @SerializedName("started_at") val startedAt: Date,
    @SerializedName("is_completed") val isCompleted: Boolean,
    val queue: QueueInfo,
    val season: SeasonType,
    val platform: Platform,
    //todo what is this
    val premier: JsonElement?,
    @SerializedName("party_rr_penaltys") val partyPenalties: List<RankingPenalty>,
    val region: Region,
    @Expose val cluster: String,
)

// todo enum
data class QueueInfo(
    @Expose val id: String,
    @Expose val name: String,
    @Expose @SerializedName("mode_type") val modeType: String,
)

data class RankingPenalty(
    @Expose @SerializedName("party_id") val partyId: String,
    @Expose val penalty: Double,
)

data class PlayerInfo(
    @Expose @SerializedName("puuid") val playerId: String,
    @Expose val name: String,
    @Expose val tag: String,
    @Expose @SerializedName("team_id") val team: String,
    @Expose val platform: Platform,
    @Expose @SerializedName("party_id") val partyId: String,
    @Expose val agent: AgentType,
    @Expose val stats: BasicPlayerStats,
    @Expose @SerializedName("ability_casts") val abilityCasts: AbilityCasts,
    @Expose @SerializedName("tier") val rank: CompetitiveRankType,
    @Expose val customization: PlayerCustomisation,
    @Expose val accountLevel: Int,
    @Expose @SerializedName("session_playtime_in_ms") val sessionPlaytime: Int,
    @Expose val behavior: PlayerBehavior,
    @Expose val economy: PlayerEconomy,
)

data class PlayerCustomisation(
    @Expose val card: PlayerCardType,
    @Expose val title: PlayerTitleType,
    @Expose @SerializedName("preferred_level_border") val preferredLevelBorder: LevelBorderType?,
)

data class PlayerBehavior(
    @Expose @SerializedName("afk_rounds") val roundsAfk: Int,
    @Expose @SerializedName("friendly_fire") val friendlyFire: FriendlyFire,
    @Expose @SerializedName("rounds_in_spawn") val roundsInSpawn: Int,
)

data class FriendlyFire(
    @Expose val incoming: Int,
    @Expose val outgoing: Int,
)

data class AbilityCasts(
    @Expose val grenade: Int,
    @Expose val ability1: Int,
    @Expose val ability2: Int,
    @Expose val ultimate: Int,
)

data class BasicPlayerStats(
    @Expose val score: Int,
    @Expose val kills: Int,
    @Expose val deaths: Int,
    @Expose val assists: Int,
    @Expose val bodyshots: Int,
    @Expose val headshots: Int,
    @Expose val legshots: Int,
    @Expose val damage: PlayerDamage,
)

data class PlayerDamage(
    @Expose val dealt: Int,
    @Expose val received: Int,
)

data class PlayerEconomy(
    @Expose val spent: EconomyInfo,
    @Expose @SerializedName("loadout_value") val loadoutValue: EconomyInfo,
)

data class EconomyInfo(
    @Expose val overall: Int,
    @Expose val average: Double,
)

data class TeamInfo(
    @Expose val won: Boolean,
    @Expose val rounds: RoundsInfo,
    // todo work out what this is
    @Expose @SerializedName("premier_roster") val premierRoster: JsonElement?,
)

data class RoundsInfo(
    @Expose val won: Int,
    @Expose val lost: Int,
)

data class GameRound(
    @Expose val id: Int,
    @Expose val result: String,
    @Expose val ceremony: String,
    @Expose @SerializedName("winning_team") val winningTeam: String,
    @Expose val plant: BombEvent?,
    @Expose val defuse: BombEvent?,
    @Expose val stats: List<PlayerStats>,
)

data class BombEvent(
    @Expose @SerializedName("round_time_in_ms") val roundTime: Int,
    @Expose val site: String?,
    @Expose val location: Location,
    @Expose val player: BasicPlayerInfo,
    @Expose @SerializedName("player_locations") val playerLocations: List<PlayerLocation>,
)

data class BasicPlayerInfo(
    @Expose @SerializedName("puuid") val playerId: String,
    @Expose val name: String,
    @Expose val tag: String,
    @Expose val team: String,
)

data class PlayerLocation(
    @Expose val player: BasicPlayerInfo,
    @Expose val location: Location,
    @Expose @SerializedName("view_radians") val viewRadians: Double,
)

data class PlayerStats(
    @Expose val player: BasicPlayerInfo,
    @Expose @SerializedName("ability_casts") val abilityCasts: AbilityCasts,
    @Expose @SerializedName("damage_events") val damageEvents: List<DamageEvent>,
    // todo confirm if there is a way to get assists & deaths per round
    @Expose val stats: BasicPlayerStats,
    @Expose val economy: RoundEconomy,
    @Expose @SerializedName("was_afk") val wasAfk: Boolean,
    @Expose @SerializedName("received_penalty") val receivedPenalty: Boolean,
    @Expose @SerializedName("stayed_in_spawn") val stayedInSpawn: Boolean,
)

data class RoundEconomy(
    @Expose @SerializedName("loadout_value") val loadoutValue: Int,
    @Expose val remaining: Int,
    @Expose val weapon: WeaponType,
    @Expose val armor: GearType,
)

data class DamageEvent(
    @Expose val player: BasicPlayerInfo,
    @Expose val damage: Int,
    @Expose val headshots: Int,
    @Expose val legshots: Int,
    @Expose val bodyshots: Int,
)

data class KillEvent(
    @Expose @SerializedName("time_in_round_in_ms") val timeInRound: Int,
    @Expose @SerializedName("time_in_match_in_ms") val timeInMatch: Int,
    @Expose val round: Int,
    @Expose val killer: BasicPlayerInfo,
    @Expose val victim: BasicPlayerInfo,
    @Expose val assistants: List<BasicPlayerInfo>,
    @Expose val location: Location,
    @Expose val weapon: WeaponType,
    @Expose @SerializedName("secondary_fire_mode") val secondaryFireMode: Boolean,
    @Expose @SerializedName("player_locations") val playerLocations: List<PlayerLocation>,
)
