package com.froyout.animesapp.core.domain.usecase

import com.froyout.animesapp.core.data.Resource
import com.froyout.animesapp.core.domain.models.Anime
import com.froyout.animesapp.core.domain.repository.IAnimeRepository
import kotlinx.coroutines.flow.Flow

class AnimeInteractor(private val animeRepository: IAnimeRepository) : AnimeUseCase {
    override fun getTopAnime(): Flow<Resource<List<Anime>>> {
        return animeRepository.getTopAnime("airing")
    }

    override fun getFavoriteAnime(): Flow<List<Anime>> {
        return animeRepository.getFavoriteAnime()
    }

    override fun setFavoriteAnime(anime: Anime, state: Boolean) {
        return animeRepository.setFavoriteAnime(anime, state)
    }

    override fun getDetailAnime(id: String): Flow<Resource<Anime>> {
        return animeRepository.getDetailAnime(id)
    }


}