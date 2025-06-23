package com.example.podcastapp.presentation.details.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.example.podcastapp.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailsScreenTopBar(
    onBackPress: () -> Unit
) {
    TopAppBar(
        title = {
            Text(
                text = stringResource(R.string.back_text),
                style = MaterialTheme.typography.titleMedium
            )
        },
        navigationIcon = {
            IconButton(onBackPress) {
                Icon(
                    imageVector = Icons.AutoMirrored.Default.ArrowBack,
                    contentDescription = stringResource(R.string.back_icon_button_content_description)
                )
            }
        }
    )
}