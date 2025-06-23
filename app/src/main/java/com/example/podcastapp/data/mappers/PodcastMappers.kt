package com.example.podcastapp.data.mappers

import com.example.podcastapp.data.local.model.PodcastEntity
import com.example.podcastapp.data.local.model.PodcastWithFavoriteStatus
import com.example.podcastapp.data.remote.PodcastDto
import com.example.podcastapp.domain.model.Podcast

/**
 * Converting [PodcastDto] to [PodcastEntity] to store received objects from
 * API into database.
 */
fun PodcastDto.toPodcastEntity(): PodcastEntity {
    return PodcastEntity(
        id = id,
        title = title,
        publisher = publisher,
        imageUrl = imageUrl,
        description = description,
        bannerUrl = bannerUrl
    )
}

/**
 * Single source where data is loaded and displayed to the user.
 * We read from the cached data in [PodcastWithFavoriteStatus] and display it through
 * [Podcast].
 */
fun PodcastWithFavoriteStatus.toPodcast(): Podcast {
    return Podcast(
        id = this.podcastEntity.id,
        title = this.podcastEntity.title,
        publisher = this.podcastEntity.publisher,
        imageUrl = this.podcastEntity.imageUrl,
        description = this.podcastEntity.description,
        bannerUrl = this.podcastEntity.bannerUrl,
        isFavorite = isFavorite
    )
}