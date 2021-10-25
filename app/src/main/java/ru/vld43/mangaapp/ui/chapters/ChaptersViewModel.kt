package ru.vld43.mangaapp.ui.chapters

import android.util.Log
import androidx.lifecycle.ViewModel
import com.jakewharton.rxrelay2.PublishRelay
import io.reactivex.disposables.CompositeDisposable
import ru.vld43.mangaapp.App
import ru.vld43.mangaapp.data.MangaRepository
import ru.vld43.mangaapp.data.MangaStore
import ru.vld43.mangaapp.domain.Chapter
import ru.vld43.mangaapp.domain.extensions.applySchedulers
import javax.inject.Inject

class ChaptersViewModel : ViewModel() {

    @Inject
    lateinit var mangaRepository: MangaRepository

    @Inject
    lateinit var mangaStore: MangaStore

    private val disposables = CompositeDisposable()

    val chaptersState = PublishRelay.create<List<Chapter>>()

    init {
        App.appComponent.inject(this)
    }

    fun onStart() {
        disposables.addAll(
            loadChapters()
        )
    }

    override fun onCleared() {
        super.onCleared()
        disposables.dispose()
    }


    private fun loadChapters() =
        mangaRepository.getChapters(mangaStore.getManga().manga.id)
            .applySchedulers()
            .subscribe({
                chaptersState.accept(it)
                Log.i("qqq", "loadChapters: $it")
            }, {
                Log.i("qqq", "loadChapters: $it")
            })
}