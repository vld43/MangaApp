package ru.vld43.mangaapp.di.app

import dagger.Component
import ru.vld43.mangaapp.ui.description.DescriptionFragment
import ru.vld43.mangaapp.ui.home.HomeViewModel
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class])
interface AppComponent {

    fun inject(homeViewModel: HomeViewModel)

    fun inject(descriptionFragment: DescriptionFragment)
}