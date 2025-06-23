package com.example.podcastapp.data.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Entity data class that represents podcast table
 * in the database.
 */
@Entity(tableName = "podcast")
data class PodcastEntity(
    @PrimaryKey
    val id: String,
    val title: String,
    val publisher: String,
    val imageUrl: String,
    val description: String,
    val bannerUrl: String
)