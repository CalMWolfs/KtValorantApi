package com.calmwolfs.ktvalorantapi.models

import com.calmwolfs.ktvalorantapi.enums.Region
import com.google.gson.annotations.SerializedName

data class ValorantVersion(
    val region: Region,
    val branch: String,
    @SerializedName("build_date") val buildDate: String,
    @SerializedName("build_ver") val buildVersion: String,
    @SerializedName("last_checked") val lastChecked: Long,
    val version: String,
    @SerializedName("version_for_api") val apiVersion: String,
)
