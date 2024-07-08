package com.calmwolfs.ktvalorantapi.enums

import com.google.gson.TypeAdapter
import com.google.gson.stream.JsonReader
import com.google.gson.stream.JsonWriter

enum class Language(val locale: String, val localeUrl: String, val displayName: String) {
    ENGLISH("en_US", "en-us", "English"),
    GERMAN("de_DE", "de-de", "German"),
    SPANISH_SPAIN("es_ES", "es-es", "Spain Spanish"),
    SPANISH_MEXICO("es_MX", "es-mx", "Mexican Spanish"),
    FRENCH("fr_FR", "fr-fr", "French"),
    INDONESIAN("id_ID", "id-id", "Indonesian"),
    ITALIAN("it_IT", "it-it", "Italian"),
    PORTUGUESE("pt_BR", "pt-br", "Portuguese"),
    ARABIC("ar_AE", "ar-ae", "Arabic"),
    POLISH("pl_PL", "pl-pl", "Polish"),
    RUSSIAN("ru_RU", "ru-ru", "Russian"),
    TURKISH("tr_TR", "tr-tr", "Turkish"),
    CHINESE("zh_TW", "zh-tw", "Chinese"),
    VIETNAMESE("vi_VN", "vi-vn", "Vietnamese"),
    THAI("th_TH", "th-th", "Thai"),
    JAPANESE("ja_JP", "ja-jp", "Japanese"),
    KOREAN("ko_KR", "ko-kr", "Korean"),
    ;

    override fun toString(): String {
        return displayName
    }

    companion object {
        fun fromLocaleUrl(localeUrl: String): com.calmwolfs.ktvalorantapi.enums.Language {
            return entries.first { it.localeUrl == localeUrl }
        }

        val typeAdapter: TypeAdapter<com.calmwolfs.ktvalorantapi.enums.Language> = object : TypeAdapter<com.calmwolfs.ktvalorantapi.enums.Language>() {
            override fun write(writer: JsonWriter, value: com.calmwolfs.ktvalorantapi.enums.Language) {
                writer.value(value.localeUrl)
            }

            override fun read(reader: JsonReader): com.calmwolfs.ktvalorantapi.enums.Language {
                return com.calmwolfs.ktvalorantapi.enums.Language.Companion.fromLocaleUrl(reader.nextString())
            }
        }
    }
}