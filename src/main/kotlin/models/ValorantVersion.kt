package models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import enums.Region

data class ValorantVersion(
    @Expose val region: Region,
    @Expose val branch: String,
    @Expose @SerializedName("build_date") val buildDate: String,
    @Expose @SerializedName("build_ver") val buildVersion: String,
    @Expose @SerializedName("last_checked") val lastChecked: Long,
    @Expose val version: String,
    @Expose @SerializedName("version_for_api") val apiVersion: String,
)
