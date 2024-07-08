package models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class ValorantNews(
    @SerializedName("banner_url") val bannerUrl: String,
    // todo enum?
    val category: String,
    val date: Long,
    @SerializedName("external_link") val externalLink: String?,
    val title: String,
    val url: String,
)
