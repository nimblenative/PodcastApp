package com.example.podcastapp.presentation.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemKey
import com.example.podcastapp.R
import com.example.podcastapp.presentation.home.components.ErrorContent
import com.example.podcastapp.presentation.home.components.LoadingContent
import com.example.podcastapp.presentation.home.components.PodcastListItem
import com.example.podcastapp.presentation.home.components.TitleBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel(),
    onItemClick: (String) -> Unit
) {
    // Collect life-cycle aware UI state for loading/error messages
    val uiState by viewModel.homeUiState.collectAsStateWithLifecycle()
    // Collect life-cycle aware PagingData
    val podcasts = viewModel.podcasts.collectAsLazyPagingItems()

    // State for the snackBar
    val snackBarHostState = remember { SnackbarHostState() }

    // Effect to show snackBar when an error message is present
    LaunchedEffect(uiState.errorMessage) {
        if (uiState.errorMessage != null) {
            snackBarHostState.showSnackbar(
                message = uiState.errorMessage!!,
                duration = SnackbarDuration.Short
            )
        }
    }

    Scaffold(
        topBar = { TitleBar() },
        snackbarHost = { SnackbarHost(hostState = snackBarHostState) }
    ) { innerPadding ->

        PullToRefreshBox(
            modifier = Modifier.padding(innerPadding),
            isRefreshing = uiState.isRefreshing,
            onRefresh = viewModel::refresh
        ) {
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(
                    dimensionResource(R.dimen.screen_content_padding_medium)
                ),
                contentPadding = PaddingValues(
                    horizontal = dimensionResource(R.dimen.screen_content_padding_medium),
                    vertical = dimensionResource(R.dimen.screen_content_padding_large)
                )
            ) {
                if (podcasts.loadState.refresh is LoadState.Loading) {
                    item {
                        LoadingContent(modifier = Modifier.fillParentMaxSize())
                    }
                } else if (uiState.errorMessage != null && podcasts.itemCount == 0) {
                    item {
                        ErrorContent(
                            modifier = Modifier.fillParentMaxSize(),
                            onRetryClick = viewModel::refresh
                        )
                    }
                } else {
                    items(
                        count = podcasts.itemCount,
                        key = podcasts.itemKey { it.id }
                    ) { index ->
                        val podcast = podcasts[index]
                        if (podcast != null) {
                            PodcastListItem(
                                podcast = podcast,
                                onItemClick = onItemClick
                            )
                        }
                    }
                }
            }
        }
    }
}