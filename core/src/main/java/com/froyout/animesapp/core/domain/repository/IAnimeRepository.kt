package com.froyout.animesapp.core.domain.repository

import com.froyout.animesapp.core.data.Resource
import com.froyout.animesapp.core.domain.models.Anime
import kotlinx.coroutines.flow.Flow

interface IAnimeRepository {
    fun getTopAnime(type: String): Flow<Resource<List<Anime>>>

    fun getFavoriteAnime(): Flow<List<Anime>>

    fun setFavoriteAnime(anime: Anime, state: Boolean)

    fun getDetailAnime(id: String): Flow<Resource<Anime>>
}