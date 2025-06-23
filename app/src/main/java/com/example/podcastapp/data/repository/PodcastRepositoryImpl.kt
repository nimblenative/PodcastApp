package com.example.podcastapp.data.repository

import android.util.Log
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import androidx.room.withTransaction
import com.example.podcastapp.data.local.PodcastDatabase
import com.example.podcastapp.data.local.model.FavoriteEntity
import com.example.podcastapp.data.mappers.toPodcast
import com.example.podcastapp.data.mappers.toPodcastEntity
import com.example.podcastapp.data.remote.PodcastApiService
import com.example.podcastapp.domain.model.Podcast
import com.example.podcastapp.domain.repository.PodcastRepository
import com.example.podcastapp.domain.util.Result
import com.example.podcastapp.utils.TAG
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class PodcastRepositoryImpl @Inject constructor(
    private val podcastApiService: PodcastApiService,
    private val localDataSource: PodcastDatabase
) : PodcastRepository {

    private val podcastDao = localDataSource.podcastDao()

    override fun pagePodcasts(): Flow<PagingData<Podcast>> {
        return Pager(
            config = PagingConfig(
                pageSize = 10,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { podcastDao.pagingSource() }
        ).flow
            .map { pagingData ->
                pagingData.map { podcastEntity ->
                    podcastEntity.toPodcast()
                }
            }
    }

    override suspend fun getPodcasts(): Result<Unit> {
        return try {
            val response = podcastApiService.getPodcasts()
            val entities = response.podcasts.map { it.toPodcastEntity() }

            localDataSource.withTransaction {
                podcastDao.clearAll()
                podcastDao.addPodcasts(entities)
            }
            Result.Success(Unit)
        } catch (e: HttpException) {
            Log.e(TAG, e.response()?.errorBody().toString())
            Result.Error("Network error! Please try again.")
        } catch (e: IOException) {
            Log.e(TAG, e.stackTraceToString())
            Result.Error("Could not refresh podcasts. Please check your connection.")
        }
    }

    override suspend fun getPodcastCount(): Int {
        return podcastDao.getPodcastCount()
    }

    override fun getPodcastById(id: String): Flow<Podcast> {
        return podcastDao.getPodcastById(id).map { it.toPodcast() }
    }

    override suspend fun toggleFavoriteStatus(podcastId: String, isCurrentlyFavorite: Boolean) {
        if (isCurrentlyFavorite) {
            podcastDao.deleteFavorite(podcastId)
        } else {
            podcastDao.insertFavorite(FavoriteEntity(podcastId = podcastId))
        }
    }
}