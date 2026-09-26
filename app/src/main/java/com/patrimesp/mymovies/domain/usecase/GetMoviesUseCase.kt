package com.patrimesp.mymovies.domain.usecase

import com.patrimesp.mymovies.domain.entity.Movie
import com.patrimesp.mymovies.domain.repository.MovieRepository
import javax.inject.Inject

class GetMoviesUseCase @Inject constructor(
    private val repository: MovieRepository
) {
    suspend operator fun invoke(): List<Movie> = repository.getMovieList()
}
