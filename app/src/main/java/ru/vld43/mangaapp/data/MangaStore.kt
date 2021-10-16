package ru.vld43.mangaapp.data

import android.content.SharedPreferences
import com.google.gson.Gson
import com.jakewharton.rxrelay2.PublishRelay
import ru.vld43.mangaapp.domain.DataManga
import javax.inject.Inject

private const val SAVE_MANGA_KEY = "save_manga"
private const val GET_MANGA_KEY = "get_manga"
private const val DEFAULT_GET_MANGA_STRING = ""

class MangaStore @Inject constructor(private val sharedPreferences: SharedPreferences) {

    fun saveManga(manga: DataManga) {
        sharedPreferences.edit().putString(SAVE_MANGA_KEY, Gson().toJson(manga)).apply()
    }

//    fun getManga(): DataManga =
//        Gson().fromJson(
//            sharedPreferences.getString(
//                GET_MANGA_KEY,
//                DEFAULT_GET_MANGA_STRING
//            ), DataManga::class.java
//        )
fun getManga() =
    sharedPreferences.getString(GET_MANGA_KEY, DEFAULT_GET_MANGA_STRING)
}