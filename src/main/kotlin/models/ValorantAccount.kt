package models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import enums.Platform
import enums.Region

data class ValorantAccount(
    @Expose @SerializedName("puuid") val playerId: String,
    @Expose val region: Region,
    @Expose @SerializedName("account_level") val accountLevel: Int,
    @Expose val name: String,
    @Expose val tag: String,
    @Expose val card: String,
    @Expose val title: String,
    @Expose val platforms: List<Platform>,
    @Expose @SerializedName("updated_at") val updatedAt: Long,
)
