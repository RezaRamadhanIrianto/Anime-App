package com.froyout.animesapp

import android.app.Application
import com.froyout.animesapp.core.di.databaseModule
import com.froyout.animesapp.core.di.networkModule
import com.froyout.animesapp.core.di.repositoryModule
import com.froyout.animesapp.di.useCaseModule
import com.froyout.animesapp.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class MyApplication: Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger(Level.NONE)
            androidContext(this@MyApplication)
            modules(
                listOf(
                    databaseModule,
                    networkModule,
                    repositoryModule,
                    useCaseModule,
                    viewModelModule,
                )
            )
        }
    }
}