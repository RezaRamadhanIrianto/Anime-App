package com.froyout.animesapp.core.domain.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Anime (
    val id: String,
    val rank: String,
    val title: String,
    val url: String,
    val imageUrl: String,
    val type: String,
    val score: Double = 0.0,
    val synopsis: String?,
    val premiered: String?,
    val airing: Boolean?,

): Parcelable