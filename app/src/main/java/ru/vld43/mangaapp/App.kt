package ru.vld43.mangaapp

import android.app.Application
import ru.vld43.mangaapp.di.app.AppComponent
import ru.vld43.mangaapp.di.app.AppModule
import ru.vld43.mangaapp.di.app.DaggerAppComponent

class App : Application() {

    companion object {
        lateinit var appComponent: AppComponent
    }

    override fun onCreate() {
        super.onCreate()

        appComponent = DaggerAppComponent.builder()
            .appModule(AppModule(applicationContext))
            //.sharedPreferencesModule(SharedPreferencesModule(this))
            .build()
    }


}