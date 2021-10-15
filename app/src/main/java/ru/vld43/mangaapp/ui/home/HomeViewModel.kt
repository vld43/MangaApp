package ru.vld43.mangaapp.ui.home

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.jakewharton.rxrelay2.PublishRelay
import io.reactivex.disposables.CompositeDisposable
import ru.vld43.mangaapp.App
import ru.vld43.mangaapp.data.MangaRepository
import ru.vld43.mangaapp.data.MangaStore
import ru.vld43.mangaapp.domain.DataManga
import ru.vld43.mangaapp.domain.extensions.applySchedulers
import javax.inject.Inject


class HomeViewModel(application: Application) : AndroidViewModel(application) {

    @Inject
    lateinit var mangaRepository: MangaRepository

    @Inject
    lateinit var manga: MangaStore

    private val disposables = CompositeDisposable()

    val mangaListState = PublishRelay.create<List<DataManga>>()
    val searchMangaAction = PublishRelay.create<String>()

    init {
        App.appComponent.inject(this)
    }

    fun onStart() {
        disposables.addAll(
            loadManga(),
            observeSearch()
        )
    }

    fun onStop() {
        disposables.dispose()
    }

    private fun loadManga() =
        mangaRepository.getMangaList()
            .applySchedulers()
            .subscribe(mangaListState::accept)

    private fun observeSearch() =
        searchMangaAction
            .flatMapSingle(mangaRepository::searchManga)
            .applySchedulers()
            .subscribe(mangaListState::accept)
}