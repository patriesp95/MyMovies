package com.patrimesp.mymovies.presentation.movies.list

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.patrimesp.mymovies.domain.entity.Movie
import com.patrimesp.mymovies.ui.theme.MyMoviesTheme

@Composable
fun MovieListScreen(
    moviesViewModel: MoviesViewModel = hiltViewModel(),
    navigateToDetail: () -> Unit
) {
    val uiState by moviesViewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        moviesViewModel.getMovies()
    }

    MovieListContent(
        uiState = uiState,
        navigateToDetail = navigateToDetail
    )
}

@Composable
fun MovieListContent(
    uiState: UiState,
    navigateToDetail: () -> Unit
) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Column(modifier = Modifier.fillMaxSize()) {
            Text(
                text = "Top Movies (US)",
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.padding(horizontal = 12.dp, vertical = 16.dp)
            )

            when {
                uiState.loading -> LoadingState()
                uiState.error != null -> ErrorState(message = uiState.error)
                uiState.popularMovies.isNullOrEmpty() -> EmptyState()
                else -> MovieGrid(
                    movies = uiState.popularMovies.orEmpty(),
                    navigateToDetail = navigateToDetail
                )
            }
        }
    }
}

@Preview(
    showBackground = true,
    widthDp = 360,
    heightDp = 720
)
@Composable
private fun MovieListContentPreview() {
    MyMoviesTheme {
        MovieListContent(
            uiState = UiState(
                popularMovies = listOf(
                    previewMovie(id = 1, title = "Dune: Part Two"),
                    previewMovie(id = 2, title = "Kung Fu Panda 4"),
                    previewMovie(id = 3, title = "Godzilla x Kong"),
                    previewMovie(id = 4, title = "Alienoid"),
                    previewMovie(id = 5, title = "Road House"),
                    previewMovie(id = 6, title = "The Wages of Fear"),
                    previewMovie(id = 7, title = "Madame Web"),
                    previewMovie(id = 8, title = "Migration"),
                    previewMovie(id = 9, title = "After the Pandemic")
                )
            ),
            navigateToDetail = {}
        )
    }
}

private fun previewMovie(
    id: Int,
    title: String
) = Movie(
    id = id,
    adult = false,
    overview = "",
    popularity = 0.0,
    posterPath = null,
    releaseDate = "",
    title = title
)
