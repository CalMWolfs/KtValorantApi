package com.calmwolfs.ktvalorantapi.datatypes

data class PlayerName(val name: String, val tag: String) {
    constructor(fullName: String) : this(
        fullName.substringBefore("#").also {
            if (!fullName.contains("#")) {
                throw IllegalArgumentException("Name must contain a '#' character")
            }
        },
        fullName.substringAfter("#")
    )

    val params = mutableMapOf("name" to name, "tag" to tag)
    val displayName = "$name#$tag"

    override fun toString(): String {
        return displayName
    }
}
