package ru.vld43.mangaapp.domain

import java.io.Serializable

data class Chapter(
    val id: String,
    val title: String
) : Serializable
