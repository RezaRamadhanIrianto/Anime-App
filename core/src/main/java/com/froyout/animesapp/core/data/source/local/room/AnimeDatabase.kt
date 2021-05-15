package com.froyout.animesapp.core.data.source.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.froyout.animesapp.core.data.source.local.entity.AnimeEntity

@Database(entities = [AnimeEntity::class], version = 2, exportSchema = false)
abstract class AnimeDatabase: RoomDatabase() {
    abstract fun animeDao(): AnimeDao
}
