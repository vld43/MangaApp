package ru.vld43.mangaapp.data.response.chapters

import com.google.gson.annotations.SerializedName

data class VolumeResponse(
    @SerializedName("volume") val volume: String,
    @SerializedName("chapters") val chapters: ArrayList<ChapterResponse>
)
