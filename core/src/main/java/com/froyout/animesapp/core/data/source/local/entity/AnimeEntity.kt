package com.froyout.animesapp.core.data.source.local.entity

import android.os.Parcelable
import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Entity(tableName = "anime")
data class AnimeEntity(
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "id")
    var id: String,
    @ColumnInfo(name = "rank")
    var rank: String,
    @ColumnInfo(name = "title")
    var title: String,
    @ColumnInfo(name = "url")
    var url: String,
    @ColumnInfo(name = "imageUrl")
    var imageUrl: String,
    @ColumnInfo(name = "type")
    var type: String,
    @ColumnInfo(name = "score")
    var score: Double,
    @ColumnInfo(name = "synopsis")
    var synopsis: String?,
    @ColumnInfo(name = "premiered")
    var premiered: String?,
    @ColumnInfo(name = "airing")
    var airing: Boolean?,
    @ColumnInfo(name = "isFavorite")
    var isFavorite: Boolean = false,
)