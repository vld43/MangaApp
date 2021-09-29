package ru.vld43.mangaapp.data.response.cover

import com.google.gson.annotations.SerializedName

data class CoverResponse(
    @SerializedName("attributes") val coverAttributes: CoverAttributes
) {

    //fun transform()
}
