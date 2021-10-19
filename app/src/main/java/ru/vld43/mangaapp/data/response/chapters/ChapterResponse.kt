package ru.vld43.mangaapp.data.response.chapters

import com.google.gson.annotations.SerializedName
import ru.vld43.mangaapp.domain.Chapter

data class ChapterResponse(
    @SerializedName("chapter") val title: String,
    @SerializedName("id") val id: String
) {

    fun transform() =
        Chapter(id, title)
}
