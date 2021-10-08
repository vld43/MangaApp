package ru.vld43.mangaapp.data

import io.reactivex.Single
import ru.vld43.mangaapp.domain.DataManga

class MangaRepository(private val mangaDexApi: MangaDexApi) {

    fun getMangaList(): Single<MutableList<DataManga>> =
        mangaDexApi.getMangaList().map { mangaList ->
            mangaList.manga.map { manga ->
                manga.transform()
            }
        }.toObservable()
            .flatMapIterable { it }
            .flatMapSingle { manga ->
                mangaDexApi.getCover(manga.coverId ?: "")
                    .map { DataManga(manga, it.coverData.coverAttributes.imageName) }
            }.toList()

    fun searchManga(query: String): Single<MutableList<DataManga>> =
        mangaDexApi.searchManga(query).map { mangaList ->
            mangaList.manga.map { manga ->
                manga.transform()
            }
        }.toObservable()
            .flatMapIterable { it }
            .flatMapSingle { manga ->
                mangaDexApi.getCover(manga.coverId ?: "")
                    .map { DataManga(manga, it.coverData.coverAttributes.imageName) }
            }.toList()
}