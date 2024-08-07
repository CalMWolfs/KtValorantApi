package com.calmwolfs.ktvalorantapi.models

import com.calmwolfs.ktvalorantapi.enums.Platform
import com.calmwolfs.ktvalorantapi.enums.Region
import com.calmwolfs.valorantmodelapi.enums.PlayerCardType
import com.calmwolfs.valorantmodelapi.enums.PlayerTitleType
import com.google.gson.annotations.SerializedName
import java.util.Date

data class ValorantAccount(
    @SerializedName("puuid") val playerId: String,
    val region: Region,
    @SerializedName("account_level") val accountLevel: Int,
    val name: String,
    val tag: String,
    val card: PlayerCardType,
    val title: PlayerTitleType,
    val platforms: List<Platform>,
    @SerializedName("updated_at") val updatedAt: Date,
)
