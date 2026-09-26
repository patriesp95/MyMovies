package com.patrimesp.mymovies.data.repository

import com.patrimesp.mymovies.data.datasource.api.ApiService
import com.patrimesp.mymovies.data.mapper.toDomain
import com.patrimesp.mymovies.domain.entity.Movie
import com.patrimesp.mymovies.domain.repository.MovieRepository
import javax.inject.Inject


class MovieRepositoryImpl @Inject constructor(
    private val apiService: ApiService
) : MovieRepository {
    override suspend fun getMovieList(): List<Movie> {
        return apiService.getMovies().results.map { it.toDomain() }
    }
}
