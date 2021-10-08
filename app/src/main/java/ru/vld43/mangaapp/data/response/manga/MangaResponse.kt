package ru.vld43.mangaapp.data.response.manga

import com.google.gson.annotations.SerializedName
import ru.vld43.mangaapp.domain.Manga

data class MangaResponse(
    @SerializedName("attributes") val mangaAttributes: MangaAttributes,
    @SerializedName("id") val id: String,
    @SerializedName("relationships") val relationships: List<Relationship>,
    @SerializedName("type") val type: String
) {

    fun transform(): Manga {
        relationships.map {
            if (it.type == "cover_art") {
                return Manga(
                    id,
                    mangaAttributes.description.en,
                    it.id,
                    mangaAttributes.title.en
                )
            }
        }

        return Manga(
            id,
            mangaAttributes.description.en,
            null,
            mangaAttributes.title.en
        )

    }
}