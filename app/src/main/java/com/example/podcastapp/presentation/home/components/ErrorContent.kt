package com.example.podcastapp.presentation.home.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.podcastapp.R
import com.example.podcastapp.ui.theme.PodcastAppTheme

@Preview
@Composable
fun PreviewErrorContent() {
    PodcastAppTheme {
        ErrorContent(
            modifier = Modifier.fillMaxSize(),
            onRetryClick = {}
        )
    }
}

@Composable
fun ErrorContent(
    modifier: Modifier = Modifier,
    onRetryClick: () -> Unit
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(R.drawable.ic_connection_error),
            contentDescription = stringResource(R.string.error_image_content_description),
            contentScale = ContentScale.Crop,
            modifier = Modifier.size(
                dimensionResource(R.dimen.error_image_size)
            )
        )
        Text(
            text = stringResource(R.string.error_text),
            style = MaterialTheme.typography.titleSmall
        )
        Button(
            modifier = Modifier
                .fillMaxWidth(0.4f)
                .padding(top = dimensionResource(R.dimen.retry_button_padding_top)),
            onClick = onRetryClick
        ) {
            Text(stringResource(R.string.retry_text))
        }
    }
}