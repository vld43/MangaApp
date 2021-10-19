package ru.vld43.mangaapp.data

import android.content.SharedPreferences
import android.util.Log
import com.google.gson.Gson
import ru.vld43.mangaapp.domain.DataManga
import javax.inject.Inject

private const val MANGA_KEY = "save_manga"
private const val DEFAULT_MANGA_VALUE = ""

class MangaStore @Inject constructor(private val sharedPreferences: SharedPreferences) {

    fun saveManga(manga: DataManga) {
        sharedPreferences.edit().putString(MANGA_KEY, Gson().toJson(manga)).apply()
    }

    fun getManga(): DataManga =
        Gson().fromJson(
            sharedPreferences.getString(
                MANGA_KEY,
                DEFAULT_MANGA_VALUE
            ),
            DataManga::class.java
        )
}