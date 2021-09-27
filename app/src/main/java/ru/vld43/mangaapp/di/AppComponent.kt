package ru.vld43.mangaapp.di

import dagger.Component
import ru.vld43.mangaapp.ui.MainViewModel
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class])
interface AppComponent {

    fun inject(mainViewModel: MainViewModel)
}