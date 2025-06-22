package com.example.podcastapp.data.mappers

import com.example.podcastapp.data.local.PodcastEntity
import com.example.podcastapp.data.remote.PodcastDto
import com.example.podcastapp.domain.Podcast

/**
 * Converting [PodcastDto] to [PodcastEntity] to store received objects from
 * API into database.
 */
fun PodcastDto.toPodcastEntity(): PodcastEntity {
    return PodcastEntity(
        id = id,
        title = title,
        publisher = publisher,
        imageUrl = imageUrl
    )
}

/**
 * Single source where data is loaded and displayed to the user.
 * We read from the cached data in [PodcastEntity] and display it through
 * [Podcast].
 */
fun PodcastEntity.toPodcast(): Podcast {
    return Podcast(
        id = id,
        title = title,
        publisher = publisher,
        imageUrl = imageUrl
    )
}