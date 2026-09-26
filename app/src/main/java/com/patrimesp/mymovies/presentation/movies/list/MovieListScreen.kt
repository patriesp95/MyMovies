package com.patrimesp.mymovies.presentation.movies.list

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@Composable
fun MovieListScreen(
    moviesViewModel: MoviesViewModel = hiltViewModel(),
    navigateToDetail: () -> Unit
) {
    val uiState by moviesViewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        moviesViewModel.getMovies()
    }

    MovieListContent(navigateToDetail=navigateToDetail)
}

@Composable
fun MovieListContent(navigateToDetail: () -> Unit) {

}