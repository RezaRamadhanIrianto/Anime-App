package com.froyout.animesapp.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class AnimeResponse (
    @field:SerializedName("mal_id")
    val id: String,
    @field:SerializedName("rank")
    val rank: String,
    @field:SerializedName("title")
    val title: String,
    @field:SerializedName("url")
    val url: String,
    @field:SerializedName("image_url")
    val imageUrl: String,
    @field:SerializedName("type")
    val type: String,
    @field:SerializedName("score")
    val score: Double,
    @field:SerializedName("synopsis")
    val synopsis: String?,
    @field:SerializedName("premiered")
    val premiered: String?,
    @field:SerializedName("airing")
    val airing: Boolean?,
)