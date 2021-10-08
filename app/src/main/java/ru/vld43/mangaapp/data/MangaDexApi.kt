package ru.vld43.mangaapp.data

import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import ru.vld43.mangaapp.data.ApiConstants.GET_COVER
import ru.vld43.mangaapp.data.ApiConstants.GET_MANGA_LIST
import ru.vld43.mangaapp.data.ApiConstants.SEARCH_MANGA
import ru.vld43.mangaapp.data.ApiConstants.SEARCH_QUERY_TITLE_PARAMETER
import ru.vld43.mangaapp.data.response.cover.CoverResponse
import ru.vld43.mangaapp.data.response.manga.MangaListResponse

interface MangaDexApi {

    @GET(GET_MANGA_LIST)
    fun getMangaList(): Single<MangaListResponse>

    @GET(GET_COVER)
    fun getCover(
        @Path("id") id: String
    ): Single<CoverResponse>

    @GET(SEARCH_MANGA)
    fun searchManga(
        @Query(SEARCH_QUERY_TITLE_PARAMETER) query: String
    ): Single<CoverResponse>
}
