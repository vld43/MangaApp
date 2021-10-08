package ru.vld43.mangaapp.domain

import java.io.Serializable

data class Manga(
    val id: String,
    val description: String,
    val coverId: String?,
    val title: String
) : Serializable
