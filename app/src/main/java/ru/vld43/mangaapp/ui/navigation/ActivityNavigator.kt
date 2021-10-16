package ru.vld43.mangaapp.ui.navigation

import android.content.Context
import android.content.Intent
import ru.vld43.mangaapp.ui.manga_details.MangaDetailsActivity
import javax.inject.Inject

class ActivityNavigator @Inject constructor(private val context: Context) {

    fun openMangaDetailsScreen() {
        val intent = Intent(context, MangaDetailsActivity::class.java)
        context.startActivity(intent)
    }

}