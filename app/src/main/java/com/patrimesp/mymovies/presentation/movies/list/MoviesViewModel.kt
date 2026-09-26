package com.patrimesp.mymovies.presentation.movies.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.patrimesp.mymovies.data.response.list.MovieResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

@HiltViewModel
class MoviesViewModel @Inject constructor() : ViewModel() {

    private val _uiState = MutableStateFlow(UiState())
    val uiState: StateFlow<UiState> = _uiState.asStateFlow()

    fun getMovies() {
        viewModelScope.launch {
            _uiState.update { state -> state.copy(loading = true) }

            try {
                val popularMovies = emptyList<MovieResponse>()
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
    var popularMovies: List<MovieResponse>? = null,
    val error: String? = null,
    val loading: Boolean = false
)
