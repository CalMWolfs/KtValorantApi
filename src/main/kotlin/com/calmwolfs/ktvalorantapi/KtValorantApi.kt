package com.calmwolfs.ktvalorantapi

import com.calmwolfs.ktvalorantapi.datatypes.PlayerName
import com.calmwolfs.ktvalorantapi.datatypes.SearchSize
import com.calmwolfs.ktvalorantapi.enums.Platform
import com.calmwolfs.ktvalorantapi.enums.Region
import com.calmwolfs.ktvalorantapi.exceptions.AuthenticationException
import com.calmwolfs.ktvalorantapi.exceptions.InternalServerException
import com.calmwolfs.ktvalorantapi.exceptions.InvalidRequestException
import com.calmwolfs.ktvalorantapi.exceptions.RateLimitException
import com.calmwolfs.ktvalorantapi.exceptions.ResourceNotFoundException
import com.calmwolfs.ktvalorantapi.models.Leaderboard
import com.calmwolfs.ktvalorantapi.models.MmrHistory
import com.calmwolfs.ktvalorantapi.models.QueueStatus
import com.calmwolfs.ktvalorantapi.models.StoredMatch
import com.calmwolfs.ktvalorantapi.models.StoredMmrHistory
import com.calmwolfs.ktvalorantapi.models.ValorantAccount
import com.calmwolfs.ktvalorantapi.models.ValorantMatch
import com.calmwolfs.ktvalorantapi.models.ValorantNews
import com.calmwolfs.ktvalorantapi.models.ValorantVersion
import com.calmwolfs.ktvalorantapi.utils.GsonUtils
import com.calmwolfs.ktvalorantapi.utils.TypeAdapters.apiName
import com.calmwolfs.valorantmodelapi.enums.GamemodeType
import com.calmwolfs.valorantmodelapi.enums.MapType
import com.calmwolfs.valorantmodelapi.enums.SeasonType
import com.google.gson.JsonObject
import java.io.IOException
import java.net.HttpURLConnection
import java.net.URL

class KtValorantApi(private val apiKey: String) {

    private val projectVersion = System.getProperty("project.version")
    private val baseUrl = "https://beta.api.henrikdev.xyz/valorant"

    /**
     * Get account details for a player by their name and tag.
     *
     * @param playerName The player's name and tag.
     * @param force Whether to force the API to fetch the data from the game servers.
     * @return The player's account details
     * @throws IOException If an error occurs while sending the request
     */
    @Throws(IOException::class)
    fun getAccountDetails(playerName: PlayerName, force: Boolean = false): ValorantAccount {
        val params = if (force) mapOf("force" to "true") else mapOf()
        return sendRequest<ValorantAccount>("v2/account/${playerName.name}/${playerName.tag}", params)
    }

    /**
     * Get account details for a player by their Riot Player ID.
     *
     * @param playerId The player's Riot Player ID.
     * @param force Whether to force the API to fetch the data from the game servers.
     * @return The player's account details
     * @throws IOException If an error occurs while sending the request
     */
    @Throws(IOException::class)
    fun getAccountDetailsById(playerId: String, force: Boolean = false): ValorantAccount {
        val params = if (force) mapOf("force" to "true") else mapOf()
        return sendRequest<ValorantAccount>("v2/by-puuid/account/$playerId", params)
    }

    /**
     * Get the leaderboard for a region and platform, specify either a player's name to just get their position on
     * the leaderboard. You can also specify a season to get the leaderboard for.
     *
     * @param region The region to get the leaderboard for.
     * @param platform The platform to get the leaderboard for.
     * @param searchSize Optional: The size of the search and the page to get.
     * @param season Optional: The season to get the leaderboard for. If not supplied, the leaderboard for the current season is returned.
     * @return The leaderboard
     * @throws IOException If an error occurs while sending the request
     */
    @Throws(IOException::class)
    fun getLeaderboard(
        region: Region,
        platform: Platform,
        searchSize: SearchSize? = null,
        season: SeasonType? = null
    ): Leaderboard {
        val params = searchSize?.params ?: mutableMapOf()
        season?.let { params["season_id"] = it.uuid }

        return sendRequest<Leaderboard>("v3/leaderboard/${region.serializedName}/${platform.serializedName}", params)
    }

    /**
     * Get the leaderboard for a region and platform by a player's name, specify a season to get the leaderboard for.
     *
     * @param region The region to get the leaderboard for.
     * @param platform The platform to get the leaderboard for.
     * @param playerName The player's name and tag.
     * @param season Optional: The season to get the leaderboard for. If not supplied, the leaderboard for the current season is returned.
     * @return The leaderboard
     * @throws IOException If an error occurs while sending the request
     */
    @Throws(IOException::class)
    fun getLeaderboardByName(
        region: Region,
        platform: Platform,
        playerName: PlayerName,
        season: SeasonType? = null
    ): Leaderboard {
        val params = mutableMapOf<String, String>()
        params.putAll(playerName.params)
        season?.let { params["season_id"] = it.uuid }

        return sendRequest<Leaderboard>("v3/leaderboard/${region.serializedName}/${platform.serializedName}", params)
    }

    /**
     * Get the leaderboard for a region and platform by a player's Riot Player ID, specify a season to get the leaderboard for.
     *
     * @param region The region to get the leaderboard for.
     * @param platform The platform to get the leaderboard for.
     * @param playerId The player's Riot Player ID.
     * @param season Optional: The season to get the leaderboard for. If not supplied, the leaderboard for the current season is returned.
     * @return The leaderboard
     * @throws IOException If an error occurs while sending the request
     */
    @Throws(IOException::class)
    fun getLeaderboardById(
        region: Region,
        platform: Platform,
        playerId: String,
        season: SeasonType? = null
    ): Leaderboard {
        val params = mutableMapOf<String, String>()
        params["puuid"] = playerId
        season?.let { params["season_id"] = it.uuid }

        return sendRequest<Leaderboard>(
            "v3/by-puuid/leaderboard/${region.serializedName}/${platform.serializedName}/$playerId",
            params
        )
    }

    /**
     * Get the stored matches for a player by their name and tag, specify a search size, game mode and map to filter the results.
     *
     * @param region The region to get the stored matches for.
     * @param playerName The player's name and tag.
     * @param searchSize Optional: The size of the search and the page to get.
     * @param gameMode Optional: The game mode to filter the results by.
     * @param map Optional: The map to filter the results by.
     * @return The stored matches
     * @throws IOException If an error occurs while sending the request
     */
    @Throws(IOException::class)
    fun getStoredMatches(
        region: Region,
        playerName: PlayerName,
        searchSize: SearchSize? = null,
        gameMode: GamemodeType? = null,
        map: MapType? = null
    ): List<StoredMatch> {
        val params = searchSize?.params ?: mutableMapOf()
        gameMode?.let { params["mode"] = it.apiName() }
        map?.let { params["map"] = it.displayName }
        return sendRequestList<StoredMatch>(
            "v1/stored-matches/${region.serializedName}/${playerName.name}/${playerName.tag}",
            params
        )
    }

    /**
     * Get the stored matches for a player by their Riot Player ID, specify a search size, game mode and map to filter the results.
     *
     * @param region The region to get the stored matches for.
     * @param playerId The player's Riot Player ID.
     * @param searchSize Optional: The size of the search and the page to get.
     * @param gameMode Optional: The game mode to filter the results by.
     * @param map Optional: The map to filter the results by.
     * @return The stored matches
     * @throws IOException If an error occurs while sending the request
     */
    @Throws(IOException::class)
    fun getStoredMatchesById(
        region: Region,
        playerId: String,
        searchSize: SearchSize? = null,
        gameMode: GamemodeType? = null,
        map: MapType? = null
    ): List<StoredMatch> {
        val params = searchSize?.params ?: mutableMapOf()
        gameMode?.let { params["mode"] = it.apiName() }
        map?.let { params["map"] = it.displayName }
        return sendRequestList<StoredMatch>("v1/by-puuid/stored-matches/${region.serializedName}/$playerId", params)
    }

    /**
     * Get the stored MMR history for a player by their name and tag, specify a search size to get a certain amount of results.
     *
     * @param region The region to get the stored MMR history for.
     * @param playerName The player's name and tag.
     * @param searchSize Optional: The size of the search and the page to get. Default 10.
     * @return The stored MMR history
     * @throws IOException If an error occurs while sending the request
     */
    @Throws(IOException::class)
    fun getStoredMmrHistory(
        region: Region,
        playerName: PlayerName,
        searchSize: SearchSize = SearchSize(10)
    ): List<StoredMmrHistory> {
        val params = searchSize.params
        return sendRequestList<StoredMmrHistory>(
            "v1/stored-mmr-history/${region.serializedName}/${playerName.name}/${playerName.tag}",
            params
        )
    }

    /**
     * Get the stored MMR history for a player by their Riot Player ID, specify a search size to get a certain amount of results.
     *
     * @param region The region to get the stored MMR history for.
     * @param playerId The player's Riot Player ID.
     * @param searchSize Optional: The size of the search and the page to get. Default 10.
     * @return The stored MMR history
     * @throws IOException If an error occurs while sending the request
     */
    @Throws(IOException::class)
    fun getStoredMmrHistoryById(
        region: Region,
        playerId: String,
        searchSize: SearchSize = SearchSize(10)
    ): List<StoredMmrHistory> {
        val params = searchSize.params
        return sendRequestList<StoredMmrHistory>(
            "v1/by-puuid/stored-mmr-history/${region.serializedName}/$playerId",
            params
        )
    }

    /**
     * Get the MMR history for a player by their name and tag, specify a search size to get a certain amount of results.
     *
     * @param region The region to get the MMR history for.
     * @param playerName The player's name and tag.
     * @param searchSize Optional: The size of the search and the page to get.
     * @return The MMR history
     * @throws IOException If an error occurs while sending the request
     */
    @Throws(IOException::class)
    fun getMmrHistory(region: Region, playerName: PlayerName, searchSize: SearchSize? = null): List<MmrHistory> {
        val params = searchSize?.params ?: mutableMapOf()
        return sendRequestList<MmrHistory>(
            "v1/mmr-history/${region.serializedName}/${playerName.name}/${playerName.tag}",
            params
        )
    }

    /**
     * Get the MMR history for a player by their Riot Player ID, specify a search size to get a certain amount of results.
     *
     * @param region The region to get the MMR history for.
     * @param playerId The player's Riot Player ID.
     * @param searchSize Optional: The size of the search and the page to get.
     * @return The MMR history
     * @throws IOException If an error occurs while sending the request
     */
    @Throws(IOException::class)
    fun getMmrHistoryById(region: Region, playerId: String, searchSize: SearchSize? = null): List<MmrHistory> {
        val params = searchSize?.params ?: mutableMapOf()
        return sendRequestList<MmrHistory>("v1/by-puuid/mmr-history/${region.serializedName}/$playerId", params)
    }

    /**
     * Get the matches for a player by their name and tag, specify a search size, game mode and map to filter the results.
     *
     * @param region The region to get the matches for.
     * @param platform The platform to get the matches for.
     * @param playerName The player's name and tag.
     * @param searchSize Optional: The size of the search and the page to get.
     * @param gameMode Optional: The game mode to filter the results by.
     * @param map Optional: The map to filter the results by.
     * @return The matches
     * @throws IOException If an error occurs while sending the request
     */
    @Throws(IOException::class)
    fun getMatches(
        region: Region,
        platform: Platform,
        playerName: PlayerName,
        searchSize: SearchSize = SearchSize(10),
        gameMode: GamemodeType? = null,
        map: MapType? = null
    ): List<ValorantMatch> {
        val params = searchSize.params
        gameMode?.let { params["mode"] = it.apiName() }
        map?.let { params["map"] = it.displayName }
        return sendRequestList<ValorantMatch>(
            "v4/matches/${region.serializedName}/${platform.serializedName}/${playerName.name}/${playerName.tag}",
            params
        )
    }

    /**
     * Get the matches for a player by their Riot Player ID, specify a search size, game mode and map to filter the results.
     *
     * @param region The region to get the matches for.
     * @param platform The platform to get the matches for.
     * @param playerId The player's Riot Player ID.
     * @param searchSize Optional: The size of the search and the page to get.
     * @param gameMode Optional: The game mode to filter the results by.
     * @param map Optional: The map to filter the results by.
     * @return The matches
     * @throws IOException If an error occurs while sending the request
     */
    @Throws(IOException::class)
    fun getMatchesById(
        region: Region,
        platform: Platform,
        playerId: String,
        searchSize: SearchSize = SearchSize(10),
        gameMode: GamemodeType? = null,
        map: MapType? = null
    ): List<ValorantMatch> {
        val params = searchSize.params
        gameMode?.let { params["mode"] = it.apiName() }
        map?.let { params["map"] = it.displayName }
        return sendRequestList<ValorantMatch>(
            "v4/by-puuid/matches/${region.serializedName}/${platform.serializedName}/$playerId",
            params
        )
    }

    /**
     * Get the version of the Valorant Client for a region.
     *
     * @param region The region to get the version for.
     * @return The version of the Valorant Client
     * @throws IOException If an error occurs while sending the request
     */
    @Throws(IOException::class)
    fun getClientVersion(region: Region): ValorantVersion {
        return sendRequest<ValorantVersion>("v1/version/${region.serializedName}")
    }

    /**
     * Get the Valorant news for a language.
     *
     * @param language The language to get the news for.
     * @return The Valorant news
     * @throws IOException If an error occurs while sending the request
     */
    @Throws(IOException::class)
    fun getValorantNews(language: com.calmwolfs.ktvalorantapi.enums.Language): List<ValorantNews> {
        return sendRequestList<ValorantNews>("v1/website/${language.localeUrl}")
    }

    /**
     * Get the maintenance status for a region.
     *
     * @param region The region to get the maintenance status for.
     * @return The maintenance status
     * @throws IOException If an error occurs while sending the request
     */
    @Throws(IOException::class)
    // todo when there is maintenance serialize the data
    fun getMaintenanceStatus(region: Region): JsonObject {
        return sendRequest<JsonObject>("v1/status/${region.serializedName}")
    }

    /**
     * Get the queue status for a region.
     *
     * @param region The region to get the queue status for.
     * @return The queue status
     * @throws IOException If an error occurs while sending the request
     */
    @Throws(IOException::class)
    fun getQueueStatus(region: Region): List<QueueStatus> {
        return sendRequestList<QueueStatus>("v1/queue-status/${region.serializedName}")
    }

    @Throws(IOException::class)
    private inline fun <reified T> sendRequest(requestPath: String, params: Map<String, String> = mapOf()): T {
        val responseJsonObject = getRawJsonResponse(requestPath, params)
        return GsonUtils.gson.fromJson(responseJsonObject.getAsJsonObject("data"), T::class.java)
    }

    @Throws(IOException::class)
    private inline fun <reified T> sendRequestList(
        requestPath: String,
        params: Map<String, String> = mapOf()
    ): List<T> {
        val responseJsonObject = getRawJsonResponse(requestPath, params)
        val data = responseJsonObject.getAsJsonArray("data")
        val result = mutableListOf<T>()
        data.forEach {
            result.add(GsonUtils.gson.fromJson(it, T::class.java))
        }
        return result
    }

    @Throws(IOException::class)
    private fun getRawJsonResponse(requestPath: String, params: Map<String, String> = mapOf()): JsonObject {
        val url = if (params.isEmpty()) {
            "$baseUrl/$requestPath"
        } else {
            val parameters = params.map { "${it.key}=${it.value}" }.joinToString("&")
            "$baseUrl/$requestPath?$parameters"
        }

        val connection = URL(url).openConnection() as HttpURLConnection
        // todo remove println
        println("connecting to $url")

        connection.requestMethod = "GET"
        connection.setRequestProperty("Authorization", apiKey)
        connection.setRequestProperty("User-Agent", "KtValorantApi/$projectVersion")

        connection.connect()

        when (val responseCode = connection.responseCode) {
            200 -> Unit
            429 -> {
                val rateLimit = connection.getHeaderField("x-ratelimit-limit")
                val rateLimitReset = connection.getHeaderField("x-ratelimit-reset")
                throw RateLimitException(rateLimit, rateLimitReset)
            }

            401, 403 -> {
                throw AuthenticationException(connection.responseMessage)
            }

            400 -> {
                val (code, message) = getCodeAndMessage(connection)
                throw InvalidRequestException(code, message)
            }

            404 -> {
                val (code, message) = getCodeAndMessage(connection)
                throw ResourceNotFoundException(code, message)
            }

            500 -> {
                val (code, message) = getCodeAndMessage(connection)
                throw InternalServerException(code, message)
            }

            else -> {
                throw IOException("Unknown error response code: $responseCode")
            }
        }

        val response = connection.inputStream.bufferedReader().use { it.readText() }
        return GsonUtils.gson.fromJson(response, JsonObject::class.java)
    }

    private fun getCodeAndMessage(connection: HttpURLConnection): Pair<Int, String> {
        val response = connection.errorStream.bufferedReader().use { it.readText() }
        val json = GsonUtils.gson.fromJson(response, JsonObject::class.java)
        val error = json.getAsJsonArray("errors")[0].asJsonObject
        return Pair(error.get("code").asInt, error.get("message").asString)
    }
}
