package com.froyout.animesapp.core.di

import androidx.room.Room
import com.froyout.animesapp.core.data.AnimeRepository
import com.froyout.animesapp.core.data.source.local.LocalDataSource
import com.froyout.animesapp.core.data.source.local.room.AnimeDatabase
import com.froyout.animesapp.core.data.source.remote.RemoteDataSource
import com.froyout.animesapp.core.data.source.remote.network.ApiService
import com.froyout.animesapp.core.domain.repository.IAnimeRepository
import com.froyout.animesapp.core.utils.AppExecutor
import net.sqlcipher.database.SQLiteDatabase
import net.sqlcipher.database.SupportFactory
import okhttp3.CertificatePinner
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
        val passphrase: ByteArray = SQLiteDatabase.getBytes("froyout".toCharArray())
        val factory = SupportFactory(passphrase)
        Room.databaseBuilder(
            androidContext(),
            AnimeDatabase::class.java, "Anime.db"
        )
            .fallbackToDestructiveMigration()
            .openHelperFactory(factory)
            .build()
    }
}

val networkModule = module {
    single {

        val hostname = "api.jikan.moe"
        val certificatePinner = CertificatePinner.Builder()
            .add(hostname, "sha256/7WzOtAYFrxzTuC2KKPH0qCk4EB5tukcc+aX96BTmFWI=")
            .add(hostname, "sha256/jQJTbIh0grw0/1TkHSumWb+Fs0Ggogr621gT3PvPKG0=")
            .add(hostname, "sha256/Vjs8r4z+80wjNcr1YKepWQboSIRi63WsWXhIMN+eWys=")
            .build()

        OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .connectTimeout(120, TimeUnit.SECONDS)
            .readTimeout(120, TimeUnit.SECONDS)
            .certificatePinner(certificatePinner)
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