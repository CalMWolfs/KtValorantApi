package models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import enums.Region

data class ValorantVersion(
    val region: Region,
    val branch: String,
    @SerializedName("build_date") val buildDate: String,
    @SerializedName("build_ver") val buildVersion: String,
    @SerializedName("last_checked") val lastChecked: Long,
    val version: String,
    @SerializedName("version_for_api") val apiVersion: String,
)
