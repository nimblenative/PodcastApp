package com.example.podcastapp.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.example.podcastapp.domain.use_case.GetPodcastsUseCase
import com.example.podcastapp.domain.use_case.RefreshPodcastsUseCase
import com.example.podcastapp.domain.util.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    getPodcastsUseCase: GetPodcastsUseCase,
    private val refreshPodcastsUseCase: RefreshPodcastsUseCase
) : ViewModel() {

    // The stream of paginated data from the Paging 3 library
    val podcasts = getPodcastsUseCase().cachedIn(viewModelScope)

    // The mutable state for the UI
    private val _homeUiState = MutableStateFlow(HomeUiState())
    val homeUiState: StateFlow<HomeUiState> = _homeUiState.asStateFlow()

    fun refresh() {
        viewModelScope.launch {
            _homeUiState.update { it.copy(isLoading = true) }

            when (val result = refreshPodcastsUseCase()) {
                is Result.Success -> {
                    _homeUiState.update {
                        it.copy(
                            isLoading = false,
                            errorMessage = null
                        )
                    }
                }

                is Result.Error -> {
                    _homeUiState.update {
                        it.copy(
                            isLoading = false,
                            errorMessage = result.message
                        )
                    }
                }
            }
        }
    }
}