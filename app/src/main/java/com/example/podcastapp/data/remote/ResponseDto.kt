package com.example.podcastapp.data.remote

/**
 * Data class holds the list of podcasts from {/best_podcasts} endpoint.
 */
data class ResponseDto(
    val podcasts: List<PodcastDto>
)
