package com.example.podcastapp.domain.repository

import androidx.paging.PagingData
import com.example.podcastapp.domain.model.Podcast
import com.example.podcastapp.domain.util.Result
import kotlinx.coroutines.flow.Flow

interface PodcastRepository {

    /**
     * Retrieves a stream of paginated podcast data from the
     * local cache.
     */
    fun pagePodcasts(): Flow<PagingData<Podcast>>

    /**
     * Fetches the latest podcasts from the network and replaces the
     * local cache.
     */
    suspend fun getPodcasts(): Result<Unit>

    /**
     * Retrieves the item count from the local cache.
     */
    suspend fun getPodcastCount(): Int

    /**
     * Retrieves a stream of podcast data using its [id]
     * from the local cache.
     */
    fun getPodcastById(id: String): Flow<Podcast>

    /**
     * Toggles the favorite podcast and updates the local cache.
     */
    suspend fun toggleFavoriteStatus(podcastId: String, isCurrentlyFavorite: Boolean)
}