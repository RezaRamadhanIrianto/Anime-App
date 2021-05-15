package com.froyout.animesapp.core.data.source.local

import com.froyout.animesapp.core.data.source.local.entity.AnimeEntity
import com.froyout.animesapp.core.data.source.local.room.AnimeDao
import kotlinx.coroutines.flow.Flow

class LocalDataSource(private val animeDao: AnimeDao) {
    fun getAllAnime(): Flow<List<AnimeEntity>> = animeDao.getAllAnime()

    fun getFavoriteAnime(): Flow<List<AnimeEntity>> = animeDao.getFavoriteAnime()

    fun setFavoriteAnime(animeEntity: AnimeEntity, state: Boolean) {
        animeEntity.isFavorite = state
        animeDao.updateFavoriteAnime(animeEntity)
    }

    suspend fun insertDetailAnime(animeEntity: AnimeEntity) = animeDao.insertDetailAnime(animeEntity)

    fun getDetailAnime(id: String): Flow<AnimeEntity> = animeDao.getDetailAnime(id)

}