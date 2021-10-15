package ru.vld43.mangaapp.di

import android.content.Context
import android.content.SharedPreferences
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
import ru.vld43.mangaapp.data.MangaStore
import javax.inject.Singleton


@Module
class AppModule(private val context: Context) {

    @Provides
    @Singleton
    fun provideContext() = context

    @Provides
    @Singleton
    fun provideSharedPreference(): SharedPreferences =
        context.getSharedPreferences("shared_pref", Context.MODE_PRIVATE)


    @Provides
    @Singleton
    fun provideGson(): Gson = GsonBuilder().setLenient().create()

    @Provides
    @Singleton
    fun provideRetrofit(gson: Gson): Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()

    @Provides
    @Singleton
    fun provideMangaMangaDexApi(retrofit: Retrofit): MangaDexApi =
        retrofit.create(MangaDexApi::class.java)

    @Provides
    @Singleton
    fun provideMangaRepository(mangaDexApi: MangaDexApi): MangaRepository =
        MangaRepository(mangaDexApi)


}