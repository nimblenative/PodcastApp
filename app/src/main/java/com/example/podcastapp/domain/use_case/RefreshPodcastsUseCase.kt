package com.example.podcastapp.domain.use_case

import com.example.podcastapp.domain.repository.PodcastRepository
import javax.inject.Inject

class RefreshPodcastsUseCase @Inject constructor(
    private val podcastRepository: PodcastRepository
) {
    suspend operator fun invoke() = podcastRepository.refreshPodcasts()
}