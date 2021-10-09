package ru.vld43.mangaapp.data.response.manga

import com.google.gson.annotations.SerializedName

data class Title(
    @SerializedName("en") val en: String,
    @SerializedName("ru") val ru: String,
)