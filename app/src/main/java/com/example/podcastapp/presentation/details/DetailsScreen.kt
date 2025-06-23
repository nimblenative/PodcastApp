package com.example.podcastapp.presentation.details

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.podcastapp.R
import com.example.podcastapp.domain.model.Podcast
import com.example.podcastapp.presentation.details.components.DetailsScreenTopBar
import com.example.podcastapp.ui.theme.Pink40
import com.example.podcastapp.ui.theme.Pink80
import com.example.podcastapp.ui.theme.PodcastAppTheme

@Preview
@Composable
fun PreviewDetailsScreen() {
    PodcastAppTheme {
        val podcast = Podcast(
            id = "01",
            title = "Sample Title",
            publisher = "Publisher",
            imageUrl = "img_01",
            description = "Sample Description",
            bannerUrl = "img_01"

        )
        /*DetailsScreen(
            podcast = podcast,
            onBackPress = {}
        )*/
    }
}

@Composable
fun DetailsScreen(
    onBackPress: () -> Unit,
    viewModel: DetailsViewModel = hiltViewModel()
) {
    val podcast by viewModel.podcast.collectAsStateWithLifecycle()

    Scaffold(
        topBar = { DetailsScreenTopBar(onBackPress = onBackPress) }
    ) { innerPadding ->

        if (podcast != null) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
                    .padding(
                        paddingValues = PaddingValues(
                            horizontal = dimensionResource(R.dimen.screen_content_padding_large),
                            vertical = dimensionResource(R.dimen.screen_content_padding_medium)
                        )
                    ),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(
                    dimensionResource(R.dimen.details_screen_vertical_padding)
                )
            ) {
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    verticalArrangement = Arrangement.spacedBy(
                        dimensionResource(R.dimen.screen_content_padding_small)
                    ),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = podcast!!.title,
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = podcast!!.publisher,
                        style = MaterialTheme.typography.bodyMedium,
                        color = Color.Gray
                    )
                }
                AsyncImage(
                    modifier = Modifier
                        .size(dimensionResource(R.dimen.details_screen_banner_image_size))
                        .clip(
                            RoundedCornerShape(
                                dimensionResource(R.dimen.details_screen_banner_image_corner_shape_size)
                            )
                        ),
                    model = ImageRequest.Builder(context = LocalContext.current)
                        .data(podcast!!.bannerUrl)
                        .crossfade(enable = true)
                        .build(),
                    placeholder = painterResource(R.drawable.ic_user_avatar),
                    error = painterResource(R.drawable.ic_broken_image),
                    contentDescription =
                        stringResource(
                            R.string.details_screen_image_content_description,
                            podcast!!.title
                        )
                )
                FilledTonalButton(
                    modifier = Modifier
                        .fillMaxWidth(0.3f),
                    shape = RoundedCornerShape(
                        dimensionResource(R.dimen.details_screen_button_corner_shape_size)
                    ),
                    onClick = {},
                    colors = ButtonColors(
                        contentColor = Color.White,
                        containerColor = Pink40,
                        disabledContainerColor = Pink80,
                        disabledContentColor = Color.Gray
                    )
                ) {
                    Text(stringResource(R.string.favourite_text))
                }
                Text(
                    modifier = Modifier
                        .verticalScroll(rememberScrollState()),
                    text = podcast!!.description,
                    textAlign = TextAlign.Center,
                    color = Color.Gray,
                    style = MaterialTheme.typography.bodySmall
                )
            }
        }
    }
}