package ru.vld43.mangaapp

import android.app.Application
import ru.vld43.mangaapp.di.AppComponent
import ru.vld43.mangaapp.di.AppModule
import ru.vld43.mangaapp.di.DaggerAppComponent

class App : Application() {

    companion object {
        lateinit var appComponent: AppComponent
    }

    override fun onCreate() {
        super.onCreate()

        appComponent = DaggerAppComponent.builder()
            .appModule(AppModule(applicationContext))
            .build()
    }


}