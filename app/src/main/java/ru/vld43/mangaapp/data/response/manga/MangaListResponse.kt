package ru.vld43.mangaapp.data.response.manga

import com.google.gson.annotations.SerializedName

data class MangaListResponse(
    @SerializedName("result") val result: String,
    @SerializedName("data") val manga: ArrayList<MangaResponse>,
)
