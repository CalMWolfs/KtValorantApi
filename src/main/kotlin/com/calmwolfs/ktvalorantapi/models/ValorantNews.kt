package com.calmwolfs.ktvalorantapi.models

import com.google.gson.annotations.SerializedName
import java.util.Date

data class ValorantNews(
    @SerializedName("banner_url") val bannerUrl: String,
    // todo enum?
    val category: String,
    val date: Date,
    @SerializedName("external_link") val externalLink: String?,
    val title: String,
    val url: String,
)
