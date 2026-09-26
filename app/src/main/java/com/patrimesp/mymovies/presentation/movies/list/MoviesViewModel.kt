package com.patrimesp.mymovies.presentation.movies.list

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.patrimesp.mymovies.domain.entity.Movie
import com.patrimesp.mymovies.domain.usecase.GetMoviesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

@HiltViewModel
class MoviesViewModel @Inject constructor(
    private val getMoviesUseCase: GetMoviesUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(UiState())
    val uiState: StateFlow<UiState> = _uiState.asStateFlow()

    fun getMovies() {
        viewModelScope.launch {
            _uiState.update { state -> state.copy(loading = true) }

            try {
                val popularMovies = getMoviesUseCase()
                _uiState.update { state ->
                    state.copy(
                        popularMovies = popularMovies,
                        error = null,
                        loading = false
                    )
                }
            } catch (error: Exception) {
                _uiState.update { state ->
                    state.copy(loading = false, error = error.message)
                }
            }
        }
    }
}

data class UiState(
    var popularMovies: List<Movie>? = null,
    val error: String? = null,
    val loading: Boolean = false
)