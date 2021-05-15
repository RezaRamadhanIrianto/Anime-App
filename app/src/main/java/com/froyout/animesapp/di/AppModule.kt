package com.froyout.animesapp.di

import com.froyout.animesapp.core.domain.usecase.AnimeInteractor
import com.froyout.animesapp.core.domain.usecase.AnimeUseCase
import com.froyout.animesapp.detail.DetailViewModel
import com.froyout.animesapp.home.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val useCaseModule = module {
    factory<AnimeUseCase> { AnimeInteractor(get()) }
}

val viewModelModule = module{
    viewModel { HomeViewModel(get()) }
    viewModel { DetailViewModel(get()) }
}