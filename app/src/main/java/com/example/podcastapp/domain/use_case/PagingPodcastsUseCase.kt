package com.example.podcastapp.domain.use_case

import com.example.podcastapp.domain.repository.PodcastRepository
import javax.inject.Inject

class PagingPodcastsUseCase @Inject constructor(
    private val podcastRepository: PodcastRepository
) {
    operator fun invoke() = podcastRepository.pagePodcasts()
}