package ru.vld43.mangaapp.data.response.chapters

import com.google.gson.annotations.SerializedName
import ru.vld43.mangaapp.domain.Chapter

data class ChaptersResponse(
    @SerializedName("data") val chapters: List<Chapter>
)
