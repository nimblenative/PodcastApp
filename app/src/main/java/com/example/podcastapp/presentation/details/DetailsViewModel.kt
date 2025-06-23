package com.example.podcastapp.presentation.details

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.podcastapp.domain.model.Podcast
import com.example.podcastapp.domain.use_case.GetPodcastByIdUseCase
import com.example.podcastapp.domain.use_case.ToggleFavoriteUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    getPodcastByIdUseCase: GetPodcastByIdUseCase,
    savedStateHandle: SavedStateHandle,
    private val toggleFavoriteUseCase: ToggleFavoriteUseCase
) : ViewModel() {

    private val podcastId: String = checkNotNull(savedStateHandle["podcastId"])

    val podcast: StateFlow<Podcast?> = getPodcastByIdUseCase(podcastId)
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000L),
            initialValue = null
        )

    fun onFavoriteToggle() {
        viewModelScope.launch {
            podcast.value?.let { currentPodcast ->
                toggleFavoriteUseCase(currentPodcast.id, currentPodcast.isFavorite)
            }
        }
    }
}