package ru.vld43.mangaapp.data.response.manga

import com.google.gson.annotations.SerializedName

data class Relationship(
    @SerializedName("id") val id: String,
    @SerializedName("type") val type: String
)