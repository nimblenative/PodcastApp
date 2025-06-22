package com.example.podcastapp.domain

/**
 * Data class that holds the podcast information.
 * The single source of truth.
 */
data class Podcast(
    val id: String,
    val title: String,
    val publisher: String,
    val imageUrl: String
)
