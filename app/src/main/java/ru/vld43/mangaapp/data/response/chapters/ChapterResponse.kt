package ru.vld43.mangaapp.data.response.chapters

import com.google.gson.annotations.SerializedName
import ru.vld43.mangaapp.domain.Chapter

data class ChapterResponse(
    @SerializedName("id") val id: String,
    @SerializedName("attributes") val chapterAttributes: ChapterAttributes
) {

    fun transform() =
        Chapter(
            id = id,
            number = chapterAttributes.number,
            title = chapterAttributes.title,
            hash = chapterAttributes.hash,
            data = chapterAttributes.data
        )
}
