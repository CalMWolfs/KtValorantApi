package com.calmwolfs.ktvalorantapi.enums

enum class Region(val apiName: String, val prettyName: String) {
    ASIA_PACIFIC("ap", "Asia-Pacific"),
    BRAZIL("br", "Brazil"),
    EUROPE("eu", "Europe"),
    KOREA("kr", "Korea"),
    LATIN_AMERICA("latam", "Latin America"),
    NORTH_AMERICA("na", "North America"),
    ;

    override fun toString(): String {
        return prettyName
    }

    companion object {
        fun fromSerializedName(serializedName: String): Region {
            return entries.first { it.apiName == serializedName.lowercase() }
        }
    }
}