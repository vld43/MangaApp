package ru.vld43.mangaapp.ui.home

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import ru.vld43.mangaapp.App
import ru.vld43.mangaapp.data.MangaRepository
import ru.vld43.mangaapp.domain.DataManga
import javax.inject.Inject

class HomeViewModel(application: Application) : AndroidViewModel(application) {

    @Inject
    lateinit var mangaRepository: MangaRepository

    private val disposables = CompositeDisposable()
    val liveData = MutableLiveData<List<DataManga>>()

    companion object {
        const val TAG = "MainViewModel"
    }

    init {
        App.appComponent.inject(this)
        loadManga()
    }

    override fun onCleared() {
        disposables.dispose()
        super.onCleared()
    }

    fun loadManga() {
        disposables.add(
            mangaRepository.getMangaList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ mangaList ->
                    liveData.value = mangaList
                }, {
                    Log.i(TAG, "$it")
                })
        )
    }

    fun searchManga(query: String) {
        disposables.add(
            mangaRepository.searchManga(query)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ mangaList ->
                    liveData.value = mangaList
                    Log.i(TAG, query)
                }, {
                    Log.i(TAG, "$it")
                })
        )
    }
}