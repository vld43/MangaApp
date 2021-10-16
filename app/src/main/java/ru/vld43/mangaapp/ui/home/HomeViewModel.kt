package ru.vld43.mangaapp.ui.home

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import com.jakewharton.rxrelay2.PublishRelay
import io.reactivex.disposables.CompositeDisposable
import ru.vld43.mangaapp.App
import ru.vld43.mangaapp.data.MangaRepository
import ru.vld43.mangaapp.data.MangaStore
import ru.vld43.mangaapp.domain.DataManga
import ru.vld43.mangaapp.domain.extensions.applySchedulers
import ru.vld43.mangaapp.ui.navigation.ActivityNavigator
import javax.inject.Inject


class HomeViewModel(application: Application) : AndroidViewModel(application) {

    @Inject
    lateinit var mangaRepository: MangaRepository

    @Inject
    lateinit var mangaStore: MangaStore

    @Inject
    lateinit var activityNavigator: ActivityNavigator

    private val disposables = CompositeDisposable()

    val mangaListState = PublishRelay.create<List<DataManga>>()

    val searchMangaAction = PublishRelay.create<String>()
    val saveMangaAction = PublishRelay.create<DataManga>()

    init {
        App.appComponent.inject(this)
    }

    fun onStart() {
        disposables.addAll(
            loadManga(),
            observeSearch(),
            saveManga()
        )
    }

    fun onStop() {
        disposables.dispose()
    }

    private fun loadManga() =
        mangaRepository.getMangaList()
            .applySchedulers()
            .subscribe({ mangaListState.accept(it) }, { Log.i("qq", "loadManga: $it") })

    private fun observeSearch() =
        searchMangaAction
            .flatMapSingle(mangaRepository::searchManga)
            .applySchedulers()
            .subscribe({ mangaListState.accept(it) }, { Log.i("qq", "observeSearch: $it") })

    private fun saveManga() =
        saveMangaAction
            .applySchedulers()
            .subscribe{
                mangaStore.saveManga(it)
                activityNavigator.openMangaDetailsScreen()
            }

}