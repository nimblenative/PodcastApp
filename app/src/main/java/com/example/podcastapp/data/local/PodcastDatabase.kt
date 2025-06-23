package com.example.podcastapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.podcastapp.data.local.model.FavoriteEntity
import com.example.podcastapp.data.local.model.PodcastEntity

@Database(
    entities = [PodcastEntity::class, FavoriteEntity::class],
    version = 1,
    exportSchema = false
)
abstract class PodcastDatabase : RoomDatabase() {

    abstract fun podcastDao(): PodcastDao
}