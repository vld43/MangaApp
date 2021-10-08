package ru.vld43.mangaapp.domain

import java.io.Serializable

data class Cover(
    val coverId: String?,
    val coverName: String?
) : Serializable