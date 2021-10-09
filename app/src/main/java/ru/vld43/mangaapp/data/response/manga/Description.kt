package ru.vld43.mangaapp.data.response.manga

import com.google.gson.annotations.SerializedName

data class Description(
    @SerializedName("en") val en: String,
    @SerializedName("zh") val zh: String,
    @SerializedName("ru") val ru: String
)