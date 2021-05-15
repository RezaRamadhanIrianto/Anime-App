package com.froyout.animesapp.core.data.source.remote.network

import com.froyout.animesapp.core.data.source.remote.response.AnimeResponse
import com.froyout.animesapp.core.data.source.remote.response.template.ListTopAnimeResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET("top/anime/{page}")
    suspend fun getTopAnime(
        @Path("page") page :Int,
    ): ListTopAnimeResponse

    @GET("anime/{id}")
    suspend fun getDetailAnime(
        @Path("id") id: String,
    ): AnimeResponse
}