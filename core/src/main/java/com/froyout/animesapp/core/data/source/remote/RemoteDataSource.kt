package com.froyout.animesapp.core.data.source.remote

import android.util.Log
import com.froyout.animesapp.core.data.source.remote.network.ApiResponse
import com.froyout.animesapp.core.data.source.remote.network.ApiService
import com.froyout.animesapp.core.data.source.remote.response.AnimeResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import java.lang.Exception

class RemoteDataSource(private val apiService: ApiService) {
    suspend fun getTopAnime(): Flow<ApiResponse<List<AnimeResponse>>>{
        return flow {
            try{
                val response = apiService.getTopAnime(1)
                val dataArray = response.results
                if(dataArray.isNotEmpty()){
                    emit(ApiResponse.Success(response.results))
                }else{
                    emit(ApiResponse.Empty)
                }
            } catch(e: Exception){
                emit(ApiResponse.Error(e.toString()))
                Log.d("RemoteDataSource", "getTopAnime: $e")
            }
        }.flowOn(Dispatchers.IO)
    }

    suspend fun getDetailAnime(id: String): Flow<ApiResponse<AnimeResponse>>{
        return flow{
            try{
                val response = apiService.getDetailAnime(id)
                if(response.id != ""){
                    emit(ApiResponse.Success(response))
                }else{
                    emit(ApiResponse.Empty)
                }
            } catch(e: Exception){
                emit(ApiResponse.Error(e.toString()))
                Log.d("RemoteDataSource", "getDetailAnime: $e")
            }
        }.flowOn(Dispatchers.IO)
    }
}