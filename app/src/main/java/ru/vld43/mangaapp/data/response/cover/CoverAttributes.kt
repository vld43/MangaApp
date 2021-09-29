package ru.vld43.mangaapp.data.response.cover

import com.google.gson.annotations.SerializedName

data class CoverAttributes(
    @SerializedName("fileName") val imageName: String
)
