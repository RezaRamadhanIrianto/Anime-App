package com.froyout.animesapp.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.froyout.animesapp.core.domain.usecase.AnimeUseCase

class HomeViewModel(animeUsecase: AnimeUseCase) : ViewModel(){
    val animes = animeUsecase.getTopAnime().asLiveData()
}