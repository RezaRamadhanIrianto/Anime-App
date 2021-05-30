package com.froyout.animesapp.favorite

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.froyout.animesapp.core.domain.models.Anime
import com.froyout.animesapp.core.domain.usecase.AnimeUseCase

class FavoriteViewModel(private val animeUseCase: AnimeUseCase): ViewModel() {
    fun getFavorite(): LiveData<List<Anime>>{
        return animeUseCase.getFavoriteAnime().asLiveData()
    }
}