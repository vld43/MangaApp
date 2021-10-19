package ru.vld43.mangaapp.data

object ApiConstants {

    const val BASE_URL = "https://api.mangadex.org"
    const val COVER_URL = "https://uploads.mangadex.org/covers/"

    const val GET_MANGA_LIST = "manga"
    const val GET_MANGA = "manga/{id}"
    const val GET_COVERS = "cover"
    const val GET_COVER = "cover/{id}"
    const val SEARCH_MANGA = "$BASE_URL/manga"
    const val GET_MANGA_CHAPTERS = "$BASE_URL/{id}/aggregate"

    const val COVER_SIZE = ".256.jpg"

    const val SEARCH_QUERY_TITLE_PARAMETER = "title"
}