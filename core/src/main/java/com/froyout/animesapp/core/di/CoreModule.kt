package com.froyout.animesapp.core.di

import androidx.room.Room
import com.froyout.animesapp.core.data.AnimeRepository
import com.froyout.animesapp.core.data.source.local.LocalDataSource
import com.froyout.animesapp.core.data.source.local.room.AnimeDatabase
import com.froyout.animesapp.core.data.source.remote.RemoteDataSource
import com.froyout.animesapp.core.data.source.remote.network.ApiService
import com.froyout.animesapp.core.domain.repository.IAnimeRepository
import com.froyout.animesapp.core.utils.AppExecutor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val databaseModule = module {
    factory { get<AnimeDatabase>().animeDao() }
    single {
        Room.databaseBuilder(
            androidContext(),
            AnimeDatabase::class.java, "Anime.db"
        )
            .fallbackToDestructiveMigration().build()
    }
}

val networkModule = module {
    single {
        OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .connectTimeout(120, TimeUnit.SECONDS)
            .readTimeout(120, TimeUnit.SECONDS)
            .build()
    }
    single {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.jikan.moe/v3/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(get())
            .build()
        retrofit.create(ApiService::class.java)
    }
}

val repositoryModule = module {
    single { LocalDataSource(get()) }
    single { RemoteDataSource(get()) }
    factory { AppExecutor() }
    single<IAnimeRepository> {
        AnimeRepository(
            get(),
            get(),
            get()
        )
    }
}