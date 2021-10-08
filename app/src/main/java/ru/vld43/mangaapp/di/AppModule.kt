package ru.vld43.mangaapp.di

import android.content.Context
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import ru.vld43.mangaapp.data.ApiConstants.BASE_URL
import ru.vld43.mangaapp.data.MangaDexApi
import ru.vld43.mangaapp.data.MangaRepository
import javax.inject.Singleton


@Module
class AppModule(private val context: Context) {

    @Provides
    @Singleton
    fun provideContext() = context

    @Provides
    @Singleton
    fun provideGson(): Gson = GsonBuilder().setLenient().create()

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create(provideGson()))
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()

    @Provides
    @Singleton
    fun provideMangaMangaDexApi(): MangaDexApi = provideRetrofit().create(MangaDexApi::class.java)

    @Provides
    @Singleton
    fun provideMangaRepository(): MangaRepository = MangaRepository(provideMangaMangaDexApi())
}