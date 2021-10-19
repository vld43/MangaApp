package ru.vld43.mangaapp.ui.description

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.jakewharton.rxrelay2.PublishRelay
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import ru.vld43.mangaapp.App
import ru.vld43.mangaapp.data.MangaStore
import ru.vld43.mangaapp.domain.DataManga
import ru.vld43.mangaapp.domain.extensions.applySchedulers
import javax.inject.Inject

class DescriptionViewModel(application: Application) : AndroidViewModel(application) {

    @Inject
    lateinit var mangaStore: MangaStore

    private val disposables = CompositeDisposable()

    val mangaState = PublishRelay.create<DataManga>()

    init {
        App.appComponent.inject(this)
    }

    fun onStart() {
        disposables.addAll(
            loadManga()
        )
    }

    override fun onCleared() {
        super.onCleared()
        disposables.dispose()
    }

    private fun loadManga() =
        Observable.create<DataManga> {
            it.onNext(mangaStore.getManga())
        }
            .applySchedulers()
            .subscribe(mangaState::accept)


}