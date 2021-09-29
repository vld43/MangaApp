package ru.vld43.mangaapp.data

class MangaRepository(private val mangaDexApi: MangaDexApi) {

    fun getMangaList() =
        mangaDexApi.getMangaList().map { mangaList ->
            mangaList.manga.map { manga ->
                manga.transform()
            }
        }

}