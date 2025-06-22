package com.example.podcastapp.di

import android.content.Context
import androidx.room.Room
import com.example.podcastapp.data.local.PodcastDao
import com.example.podcastapp.data.local.PodcastDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun providePodcastDatabase(@ApplicationContext context: Context): PodcastDatabase {
        return Room.databaseBuilder(
            context,
            PodcastDatabase::class.java,
            "podcast.db"
        ).build()
    }

    @Provides
    @Singleton
    fun providePodcastDao(database: PodcastDatabase): PodcastDao {
        return database.podcastDao()
    }
}