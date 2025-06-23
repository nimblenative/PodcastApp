package com.example.podcastapp.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.example.podcastapp.domain.use_case.GetPodcastCountUseCase
import com.example.podcastapp.domain.use_case.GetPodcastsUseCase
import com.example.podcastapp.domain.use_case.PagingPodcastsUseCase
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
    pagingPodcastsUseCase: PagingPodcastsUseCase,
    private val getPodcastsUseCase: GetPodcastsUseCase,
    private val getPodcastCountUseCase: GetPodcastCountUseCase
) : ViewModel() {

    // The stream of paginated data from the Paging 3 library
    val podcasts = pagingPodcastsUseCase().cachedIn(viewModelScope)

    // The mutable state for the UI
    private val _homeUiState = MutableStateFlow(HomeUiState())
    val homeUiState: StateFlow<HomeUiState> = _homeUiState.asStateFlow()

    init {
        initialLoadOrRefresh()
    }

    private fun initialLoadOrRefresh() {
        viewModelScope.launch {
            if (getPodcastCountUseCase() == 0) {
                _homeUiState.update { it.copy(isLoading = true) }
                val result = getPodcastsUseCase()
                _homeUiState.update {
                    it.copy(
                        isLoading = false,
                        errorMessage = if (result is Result.Error) result.message else null
                    )
                }
            }
        }
    }

    fun refresh() {
        viewModelScope.launch {
            _homeUiState.update { it.copy(isRefreshing = true) }

            when (val result = getPodcastsUseCase()) {
                is Result.Success -> {
                    _homeUiState.update {
                        it.copy(
                            isLoading = false,
                            isRefreshing = false,
                            errorMessage = null
                        )
                    }
                }

                is Result.Error -> {
                    _homeUiState.update {
                        it.copy(
                            isLoading = false,
                            isRefreshing = false,
                            errorMessage = result.message
                        )
                    }
                }
            }
        }
    }
}