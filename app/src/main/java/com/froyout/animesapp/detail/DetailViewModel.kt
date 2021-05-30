package com.froyout.animesapp.detail


import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.froyout.animesapp.core.data.Resource
import com.froyout.animesapp.core.domain.models.Anime
import com.froyout.animesapp.core.domain.usecase.AnimeUseCase

class DetailViewModel(private val animeUseCase: AnimeUseCase) : ViewModel(){
    fun getDetailAnime(id: String): LiveData<Resource<Anime>>{
        return animeUseCase.getDetailAnime(id).asLiveData()
    }

    fun setFavoriteAnime(anime: Anime, state: Boolean){
        animeUseCase.setFavoriteAnime(anime, state)
    }
}