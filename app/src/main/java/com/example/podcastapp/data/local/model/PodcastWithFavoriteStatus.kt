package com.example.podcastapp.data.local.model

import androidx.room.Embedded

data class PodcastWithFavoriteStatus(
    // This annotation tells room to flatten the PodcastEntity's columns
    // into this data class as if they were declared here directly.
    @Embedded
    val podcastEntity: PodcastEntity,
    val isFavorite: Boolean
)
