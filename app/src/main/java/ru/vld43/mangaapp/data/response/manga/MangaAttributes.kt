package ru.vld43.mangaapp.data.response.manga

import com.google.gson.annotations.SerializedName

data class MangaAttributes(
    @SerializedName("contentRating") val contentRating: String,
    @SerializedName("description") val description: Description,
    @SerializedName("originalLanguage") val originalLanguage: String,
    @SerializedName("title") val title: Title,
)