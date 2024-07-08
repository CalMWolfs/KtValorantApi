package models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import enums.Platform
import enums.Region

data class ValorantAccount(
    @SerializedName("puuid") val playerId: String,
    val region: Region,
    @SerializedName("account_level") val accountLevel: Int,
    val name: String,
    val tag: String,
    val card: String,
    val title: String,
    val platforms: List<Platform>,
    @SerializedName("updated_at") val updatedAt: Long,
)
