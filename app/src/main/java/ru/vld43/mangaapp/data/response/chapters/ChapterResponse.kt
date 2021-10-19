package ru.vld43.mangaapp.data.response.chapters

import com.google.gson.annotations.SerializedName

data class ChapterResponse(
    @SerializedName("chapter") val chapter: String,
    @SerializedName("id") val id: String
)
