package com.example.podcastapp.data.local

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Upsert
import com.example.podcastapp.data.local.model.FavoriteEntity
import com.example.podcastapp.data.local.model.PodcastEntity
import com.example.podcastapp.data.local.model.PodcastWithFavoriteStatus
import kotlinx.coroutines.flow.Flow

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

    @Transaction
    @Query(
        """
       SELECT podcast.*, (favorite.podcastId IS NOT NULL) as isFavorite 
       FROM podcast
       LEFT JOIN favorite ON podcast.id = favorite.podcastId
    """
    )
    fun pagingSource(): PagingSource<Int, PodcastWithFavoriteStatus>

    @Query("SELECT COUNT(*) FROM podcast")
    suspend fun getPodcastCount(): Int

    @Transaction
    @Query(
        """
        SELECT podcast.*, (favorite.podcastId IS NOT NULL) as isFavorite
        FROM podcast
        LEFT JOIN favorite ON podcast.id = favorite.podcastId
        WHERE podcast.id = :id
    """
    )
    fun getPodcastById(id: String): Flow<PodcastWithFavoriteStatus>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertFavorite(favorite: FavoriteEntity)

    @Query("DELETE FROM favorite WHERE podcastId = :podcastId")
    suspend fun deleteFavorite(podcastId: String)

}