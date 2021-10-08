package ru.vld43.mangaapp.data

import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.Path
import ru.vld43.mangaapp.data.ApiConstants.GET_COVER
import ru.vld43.mangaapp.data.ApiConstants.GET_COVERS
import ru.vld43.mangaapp.data.ApiConstants.GET_MANGA_LIST
import ru.vld43.mangaapp.data.response.cover.CoverResponse
import ru.vld43.mangaapp.data.response.cover.CoversResponse
import ru.vld43.mangaapp.data.response.manga.MangaListResponse

interface MangaDexApi {

    @GET(GET_MANGA_LIST)
    fun getMangaList(): Single<MangaListResponse>

    @GET(GET_COVER)
    fun getCover(
        @Path("id") id: String
    ): Single<CoverResponse>

    @GET(GET_COVERS)
    fun getCoverList(): Single<CoversResponse>
}
