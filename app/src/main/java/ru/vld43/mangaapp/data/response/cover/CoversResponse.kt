package ru.vld43.mangaapp.data.response.cover

import com.google.gson.annotations.SerializedName

data class CoversResponse(
    @SerializedName("data") val covers: ArrayList<CoverResponse>
)
