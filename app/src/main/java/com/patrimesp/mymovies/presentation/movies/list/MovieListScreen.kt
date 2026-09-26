package com.patrimesp.mymovies.presentation.movies.list

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun MovieListScreen(
    modifier: Modifier = Modifier,
    navigateToDetail: () -> Unit
) {
    MovieListContent()
}

@Composable
fun MovieListContent() {
    Text("Movie list screen")
}