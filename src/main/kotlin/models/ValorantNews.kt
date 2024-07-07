package models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class ValorantNews(
    @Expose @SerializedName("banner_url") val bannerUrl: String,
    // todo enum?
    @Expose val category: String,
    @Expose val date: Long,
    @Expose @SerializedName("external_link") val externalLink: String?,
    @Expose val title: String,
    @Expose val url: String,
)
