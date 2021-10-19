package ru.vld43.mangaapp.ui.home

import androidx.lifecycle.ViewModel
import com.jakewharton.rxrelay2.PublishRelay
import io.reactivex.disposables.CompositeDisposable
import ru.vld43.mangaapp.App
import ru.vld43.mangaapp.data.MangaRepository
import ru.vld43.mangaapp.data.MangaStore
import ru.vld43.mangaapp.domain.DataManga
import ru.vld43.mangaapp.domain.extensions.applySchedulers
import ru.vld43.mangaapp.ui.navigation.ActivityNavigator
import javax.inject.Inject


class HomeViewModel : ViewModel() {

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

    override fun onCleared() {
        super.onCleared()
        disposables.dispose()
    }

    private fun loadManga() =
        mangaRepository.getMangaList()
            .applySchedulers()
            .subscribe(mangaListState::accept)

    private fun observeSearch() =
        searchMangaAction
            .flatMapSingle {
                mangaRepository.searchManga(it)
                    .applySchedulers()
            }
            .subscribe {
                mangaListState.accept(it)
            }

    private fun saveManga() =
        saveMangaAction
            .applySchedulers()
            .subscribe {
                mangaStore.saveManga(it)
                //activityNavigator.openMangaDetailsScreen()
            }

}