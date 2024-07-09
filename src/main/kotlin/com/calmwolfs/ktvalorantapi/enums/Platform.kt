package com.calmwolfs.ktvalorantapi.enums

enum class Platform(val apiName: String, val prettyName: String) {
    PC("pc", "Computer"),
    CONSOLE("console", "Console"),
    ;

    override fun toString(): String {
        return prettyName
    }

    companion object {
        fun fromSerializedName(serializedName: String): Platform {
            return entries.first { it.apiName == serializedName.lowercase() }
        }
    }
}