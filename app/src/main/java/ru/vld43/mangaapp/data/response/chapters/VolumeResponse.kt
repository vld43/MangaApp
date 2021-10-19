package ru.vld43.mangaapp.data.response.chapters

import com.google.gson.annotations.SerializedName

data class VolumeResponse(
    @SerializedName("count") val count: Int,
    @SerializedName("chapters") val chapters: ArrayList<ChapterResponse>
)
