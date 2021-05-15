package com.froyout.animesapp.detail


import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.froyout.animesapp.core.data.Resource
import com.froyout.animesapp.core.domain.models.Anime
import com.froyout.animesapp.core.domain.usecase.AnimeUseCase

class DetailViewModel(private val animeUseCase: AnimeUseCase) : ViewModel(){
    fun getDetailAnime(id: String): LiveData<Resource<Anime>>{
        val anime =  animeUseCase.getDetailAnime(id).asLiveData()
        return anime
    }
}