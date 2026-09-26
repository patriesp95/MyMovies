package com.patrimesp.mymovies.presentation.movies.detail

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun MovieDetailScreen(
    modifier: Modifier = Modifier,
    navigateBack: () -> Unit
) {
    MovieDetailContent()
}

@Composable
fun MovieDetailContent() {
    Text("Movie detail screen")
}