package ru.vld43.mangaapp.data.response.chapters

import com.google.gson.annotations.SerializedName

data class VolumesResponse (
    @SerializedName("volumes") val volumeResponses: ArrayList<VolumeResponse>
)