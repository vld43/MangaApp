package ru.vld43.mangaapp.data.response.cover

import com.google.gson.annotations.SerializedName
import ru.vld43.mangaapp.domain.Cover

data class CoverAttributes(
    @SerializedName("id") val id: String,
    @SerializedName("fileName") val imageName: String?
) {

    fun transform(): Cover = Cover(id, imageName)
}
