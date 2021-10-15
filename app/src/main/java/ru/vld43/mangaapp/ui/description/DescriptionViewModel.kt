package ru.vld43.mangaapp.ui.description

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import ru.vld43.mangaapp.domain.DataManga

class DescriptionViewModel(application: Application) : AndroidViewModel(application) {

    val liveData = MutableLiveData<DataManga>()

    init {

    }

    fun loadMana(dataManga: DataManga) {
        liveData.value = dataManga
    }
}