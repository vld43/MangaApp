package ru.vld43.mangaapp.data.response.cover

import com.google.gson.annotations.SerializedName

data class CoverResponse(
    @SerializedName("data") val coverData: CoverData
)
