package ru.vld43.mangaapp.ui.main

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import ru.vld43.mangaapp.App

class MainViewModel(application: Application): AndroidViewModel(application) {

    init {
        App.appComponent.inject(this)
    }
}