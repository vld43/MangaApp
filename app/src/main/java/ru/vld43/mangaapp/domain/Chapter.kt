package ru.vld43.mangaapp.domain

import java.io.Serializable

data class Chapter(
    val id: String,
    val number: String,
    val title: String,
    val hash: String,
    val data: List<String>
) : Serializable
