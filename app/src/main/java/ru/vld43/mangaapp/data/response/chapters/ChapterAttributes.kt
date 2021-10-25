package ru.vld43.mangaapp.data.response.chapters

import com.google.gson.annotations.SerializedName

data class ChapterAttributes(
    @SerializedName("chapter") val number: String,
    @SerializedName("title") val title: String,
    @SerializedName("hash") val hash: String,
    @SerializedName("data") val data: ArrayList<String>
)
