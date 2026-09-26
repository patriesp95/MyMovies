package com.patrimesp.mymovies.presentation.movies.detail

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.patrimesp.mymovies.domain.entity.Movie
import com.patrimesp.mymovies.ui.theme.MyMoviesTheme

@Composable
fun MovieDetailScreen(
    movieId: Int,
    navigateBack: () -> Unit,
    movieDetailViewModel: MovieDetailViewModel = hiltViewModel()
) {
    val uiState by movieDetailViewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(movieId) {
        movieDetailViewModel.getMovieDetail(movieId)
    }

    when {
        uiState.loading -> MovieDetailLoadingState()
        uiState.error != null -> MovieDetailErrorState(
            message = uiState.error.orEmpty(),
            navigateBack = navigateBack
        )
        uiState.movie != null -> MovieDetailContent(
            movie = requireNotNull(uiState.movie),
            navigateBack = navigateBack
        )
        else -> MovieDetailEmptyState(navigateBack = navigateBack)
    }
}

@Preview(
    showBackground = true,
    widthDp = 360,
    heightDp = 720
)
@Composable
private fun MovieDetailScreenPreview() {
    MyMoviesTheme {
        MovieDetailContent(
            movie = Movie(
                id = 693134,
                adult = false,
                overview = "Paul Atreides se une a Chani y a los Fremen mientras busca venganza contra quienes destruyeron a su familia.",
                popularity = 120.0,
                releaseDate = "2024-02-27",
                title = "Dune: Parte dos",
                runtime = 166,
                voteAverage = 8.2,
                genres = listOf("Ciencia ficción", "Aventura")
            ),
            navigateBack = {}
        )
    }
}
