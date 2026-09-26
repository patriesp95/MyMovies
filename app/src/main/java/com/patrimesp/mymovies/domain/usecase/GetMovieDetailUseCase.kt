package com.patrimesp.mymovies.domain.usecase

import com.patrimesp.mymovies.domain.entity.Movie
import com.patrimesp.mymovies.domain.repository.MovieRepository
import javax.inject.Inject

class GetMovieDetailUseCase @Inject constructor(
    private val repository: MovieRepository
) {
    suspend operator fun invoke(movieId: Int): Movie = repository.getMovieDetail(movieId)
}
