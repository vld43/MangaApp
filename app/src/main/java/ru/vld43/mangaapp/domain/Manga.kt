package ru.vld43.mangaapp.domain

import java.io.Serializable

data class Manga(
    val id: String,
    val description: String,
    val coverArtId: String?,
    val title: String
) : Serializable