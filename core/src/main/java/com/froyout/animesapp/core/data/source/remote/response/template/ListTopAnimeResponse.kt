package com.froyout.animesapp.core.data.source.remote.response.template

import com.froyout.animesapp.core.data.source.remote.response.AnimeResponse
import com.google.gson.annotations.SerializedName


data class ListTopAnimeResponse (
    @field:SerializedName("message")
    val message: String,
    @field:SerializedName("status")
    val status: Int,
    @field:SerializedName("top")
    val results: List<AnimeResponse>
)
