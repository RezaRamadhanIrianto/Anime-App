package com.froyout.animesapp.core.data

import android.net.Network
import android.util.Log
import com.froyout.animesapp.core.data.source.NetworkBoundResource
import com.froyout.animesapp.core.data.source.local.LocalDataSource
import com.froyout.animesapp.core.data.source.remote.RemoteDataSource
import com.froyout.animesapp.core.data.source.remote.network.ApiResponse
import com.froyout.animesapp.core.data.source.remote.response.AnimeResponse
import com.froyout.animesapp.core.domain.models.Anime
import com.froyout.animesapp.core.domain.repository.IAnimeRepository
import com.froyout.animesapp.core.utils.AppExecutor
import com.froyout.animesapp.core.utils.DataMapper
import kotlinx.coroutines.flow.*

class AnimeRepository(
    private val localDataSource: LocalDataSource,
    private val remoteDataSource: RemoteDataSource,
    private val appExecutor: AppExecutor
): IAnimeRepository {
    override fun getTopAnime(type: String): Flow<Resource<List<Anime>>> {
        return flow{
            when(val dataArray = remoteDataSource.getTopAnime().first()){
                is ApiResponse.Success -> {
                    val listAnime = DataMapper.mapResponsesToDomain(dataArray.data)
                    emit(Resource.Success<List<Anime>>(listAnime))
                }
                is ApiResponse.Empty ->{
                    emit(Resource.Success<List<Anime>>(ArrayList<Anime>()))
                }
                is ApiResponse.Error -> {
                    emit(Resource.Error<List<Anime>>(dataArray.errorMessage))
                }
            }
        }
    }

    override fun getFavoriteAnime(): Flow<List<Anime>> {
        return localDataSource.getFavoriteAnime().map { DataMapper.mapEntitiesToDomain(it) }
    }

    override fun setFavoriteAnime(anime: Anime, state: Boolean) {
        val animeEntity = DataMapper.mapDomainToEntity(anime)
        return localDataSource.setFavoriteAnime(animeEntity, state)
    }

    override fun getDetailAnime(id: String): Flow<Resource<Anime>> =
        object : NetworkBoundResource<Anime, AnimeResponse>(appExecutor){
            override fun loadFromDB(): Flow<Anime> {
                return localDataSource.getDetailAnime(id).map { DataMapper.mapEntityToDomain(it) }
            }

            override fun shouldFetch(data: Anime): Boolean {
                return data.id == ""
            }

            override suspend fun createCall(): Flow<ApiResponse<AnimeResponse>> {
                Log.d("TAG", "createCall: $id")
                return remoteDataSource.getDetailAnime(id)
            }

            override suspend fun saveCallResult(data: AnimeResponse) {
                val animeEntity = DataMapper.mapResponseToEntity(data)
                localDataSource.insertDetailAnime(animeEntity)
            }

        }.asFlow()
}