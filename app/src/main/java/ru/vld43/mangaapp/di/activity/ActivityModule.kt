package ru.vld43.mangaapp.di.activity

import android.content.Context
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ActivityModule(private val context: Context) {

    @Provides
    @Singleton
    fun provideContext(): Context = context
}