package com.example.podcastapp.domain.use_case

import com.example.podcastapp.domain.repository.PodcastRepository
import javax.inject.Inject

class ToggleFavoriteUseCase @Inject constructor(
    private val podcastRepository: PodcastRepository
) {
    suspend operator fun invoke(podcastId: String, isCurrentlyFavorite: Boolean) {
        podcastRepository.toggleFavoriteStatus(podcastId, isCurrentlyFavorite)
    }
}