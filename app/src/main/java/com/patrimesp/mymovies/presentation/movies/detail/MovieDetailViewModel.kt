package com.patrimesp.mymovies.presentation.movies.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.patrimesp.mymovies.domain.entity.Movie
import com.patrimesp.mymovies.domain.usecase.GetMovieDetailUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

@HiltViewModel
class MovieDetailViewModel @Inject constructor(
    private val getMovieDetailUseCase: GetMovieDetailUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(MovieDetailUiState())
    val uiState: StateFlow<MovieDetailUiState> = _uiState.asStateFlow()

    fun getMovieDetail(movieId: Int) {
        viewModelScope.launch {
            _uiState.update { state -> state.copy(loading = true, error = null) }

            try {
                val movie = getMovieDetailUseCase(movieId)
                _uiState.update { state ->
                    state.copy(movie = movie, loading = false)
                }
            } catch (error: Exception) {
                _uiState.update { state ->
                    state.copy(loading = false, error = error.message)
                }
            }
        }
    }
}

data class MovieDetailUiState(
    val movie: Movie? = null,
    val error: String? = null,
    val loading: Boolean = false
)
