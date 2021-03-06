package ru.vld43.mangaapp.data

import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import ru.vld43.mangaapp.data.ApiConstants.CHAPTERS_QUERY_TITLE_PARAMETER
import ru.vld43.mangaapp.data.ApiConstants.GET_COVER
import ru.vld43.mangaapp.data.ApiConstants.GET_MANGA_CHAPTERS
import ru.vld43.mangaapp.data.ApiConstants.GET_MANGA_LIST
import ru.vld43.mangaapp.data.ApiConstants.SEARCH_MANGA
import ru.vld43.mangaapp.data.ApiConstants.SEARCH_QUERY_TITLE_PARAMETER
import ru.vld43.mangaapp.data.response.chapters.ChaptersResponse
import ru.vld43.mangaapp.data.response.cover.CoverResponse
import ru.vld43.mangaapp.data.response.manga.MangaListResponse

interface MangaDexApi {

    @GET(GET_MANGA_LIST)
    fun getMangaList(): Single<MangaListResponse>

    @GET(SEARCH_MANGA)
    fun searchManga(
        @Query(SEARCH_QUERY_TITLE_PARAMETER) query: String
    ): Single<MangaListResponse>

    @GET(GET_COVER)
    fun getCover(
        @Path("id") id: String
    ): Single<CoverResponse>

    @GET(GET_MANGA_CHAPTERS)
    fun getMangaChapters(
        @Query(CHAPTERS_QUERY_TITLE_PARAMETER) mangaId: String
    ): Single<ChaptersResponse>
}
