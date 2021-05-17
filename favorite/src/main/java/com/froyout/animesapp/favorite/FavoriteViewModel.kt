package com.froyout.animesapp.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.froyout.animesapp.core.domain.usecase.AnimeUseCase

class FavoriteViewModel(animeUseCase: AnimeUseCase): ViewModel() {
    val favoriteAnimes = animeUseCase.getFavoriteAnime().asLiveData()
}