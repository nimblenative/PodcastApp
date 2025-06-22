package com.example.podcastapp.data.remote

import com.squareup.moshi.Json

/**
 * Data class that holds the podcast information.
 * @param id of the podcast - e.g. "ee84d7d11875465fb89487675ff5425d"
 * @param title of the podcast - e.g. "THE ED MYLETT SHOW"
 * @param publisher company of the podcast - e.g. "Ed Mylett | Cumulus Podcast Network"
 * @param imageUrl of the podcast that will be processed by Coil and displayed on list item.
 */
data class PodcastDto(
    val id: String,
    val title: String,
    val publisher: String,
    @field:Json(name = "thumbnail")
    val imageUrl: String
)
