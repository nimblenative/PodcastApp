package com.example.podcastapp.data.local

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert

/**
 * Data Object Access class for [PodcastEntity] that contains queries
 * to access data.
 */
@Dao
interface PodcastDao {

    @Upsert
    suspend fun addPodcasts(podcasts: List<PodcastEntity>)

    @Query("DELETE FROM podcast")
    suspend fun clearAll()

    @Query("SELECT * FROM podcast")
    fun pagingSource(): PagingSource<Int, PodcastEntity>

    @Query("SELECT COUNT(*) FROM podcast")
    suspend fun getPodcastCount(): Int

}