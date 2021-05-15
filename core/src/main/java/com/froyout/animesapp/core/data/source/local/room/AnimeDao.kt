package com.froyout.animesapp.core.data.source.local.room

import androidx.room.*
import com.froyout.animesapp.core.data.source.local.entity.AnimeEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface AnimeDao {
    @Query("SELECT * FROM anime")
    fun getAllAnime(): Flow<List<AnimeEntity>>

    @Query("SELECT * FROM anime where isFavorite = 1")
    fun getFavoriteAnime(): Flow<List<AnimeEntity>>

    @Update
    fun updateFavoriteAnime(anime: AnimeEntity)

    @Query("SELECT * FROM anime where id = :id")
    fun getDetailAnime(id: String): Flow<AnimeEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDetailAnime(anime: AnimeEntity)
}