package ru.vld43.mangaapp.di

import dagger.Component
import ru.vld43.mangaapp.ui.home.HomeViewModel
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class])
interface AppComponent {

    fun inject(homeViewModel: HomeViewModel)
}