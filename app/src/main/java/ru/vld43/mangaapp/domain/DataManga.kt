package ru.vld43.mangaapp.domain

import java.io.Serializable

data class DataManga(
    val manga: Manga,
    val imageName: String?
) : Serializable