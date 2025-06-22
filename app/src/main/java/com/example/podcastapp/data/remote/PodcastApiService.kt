package com.example.podcastapp.data.remote

import retrofit2.http.GET

/**
 * Service class that make calls to the API using endpoints.
 **/
interface PodcastApiService {

    @GET("best_podcasts")
    suspend fun getPodcasts(): ResponseDto

}