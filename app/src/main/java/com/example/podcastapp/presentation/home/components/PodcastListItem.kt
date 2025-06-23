package com.example.podcastapp.presentation.home.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.podcastapp.R
import com.example.podcastapp.domain.model.Podcast
import com.example.podcastapp.ui.theme.Pink40
import com.example.podcastapp.ui.theme.PodcastAppTheme

@Preview(showBackground = true)
@Composable
fun PreviewPodcastListItem() {
    PodcastAppTheme {
        val podcast = Podcast(
            id = "01",
            title = "Sample Title",
            publisher = "Publisher",
            imageUrl = "img_01"
        )

        PodcastListItem(
            podcast,
            onItemClick = {}
        )
    }
}

@Composable
fun PodcastListItem(
    podcast: Podcast,
    onItemClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onItemClick() },
        verticalArrangement = Arrangement.spacedBy(
            dimensionResource(R.dimen.screen_content_padding_medium)
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(
                dimensionResource(R.dimen.screen_content_padding_medium)
            ),
            verticalAlignment = Alignment.CenterVertically
        ) {
            AsyncImage(
                modifier = Modifier
                    .size(dimensionResource(R.dimen.homeScreen_listItem_thumbnail_size))
                    .clip(
                        RoundedCornerShape(
                            dimensionResource(R.dimen.homeScreen_listItem_thumbnail_cornerShape_size)
                        )
                    ),
                model = ImageRequest.Builder(context = LocalContext.current)
                    .data(podcast.imageUrl)
                    .crossfade(enable = true)
                    .build(),
                contentDescription = stringResource(
                    R.string.podcast_listItem_image_content_description,
                    podcast.title
                ),
                contentScale = ContentScale.Crop,
                placeholder = painterResource(R.drawable.ic_user_avatar),
                error = painterResource(R.drawable.ic_broken_image)
            )
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                verticalArrangement = Arrangement.spacedBy(
                    dimensionResource(R.dimen.homeScreen_vertical_text_padding)
                )
            ) {
                Text(
                    text = podcast.title,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    style = MaterialTheme.typography.titleSmall,
                )
                Text(
                    text = podcast.publisher,
                    style = MaterialTheme.typography.bodySmall,
                    color = Color.Gray
                )
                Text(
                    text = stringResource(R.string.favourited_text),
                    style = MaterialTheme.typography.bodySmall,
                    color = Pink40
                )
            }
        }
        HorizontalDivider(
            modifier = Modifier
                .fillMaxWidth()
                .alpha(0.3f),
            color = Color.LightGray
        )
    }
}