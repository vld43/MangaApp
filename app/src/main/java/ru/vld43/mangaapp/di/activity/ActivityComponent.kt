package ru.vld43.mangaapp.di.activity

import dagger.Component
import ru.vld43.mangaapp.ui.main.MainActivity
import javax.inject.Singleton

@Singleton
@Component(modules = [ActivityModule::class])
interface ActivityComponent {

    fun inject(mainActivity: MainActivity)
}