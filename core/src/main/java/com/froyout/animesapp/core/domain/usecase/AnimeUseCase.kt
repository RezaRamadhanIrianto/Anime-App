package com.froyout.animesapp.core.domain.usecase

import com.froyout.animesapp.core.data.Resource
import com.froyout.animesapp.core.domain.models.Anime
import kotlinx.coroutines.flow.Flow

interface AnimeUseCase {
    fun getTopAnime(): Flow<Resource<List<Anime>>>

    fun getFavoriteAnime(): Flow<List<Anime>>

    fun setFavoriteAnime(anime: Anime, state: Boolean)

    fun getDetailAnime(id: String): Flow<Resource<Anime>>
}