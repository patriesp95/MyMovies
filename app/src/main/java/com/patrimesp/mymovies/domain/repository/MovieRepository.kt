package com.patrimesp.mymovies.domain.repository

import com.patrimesp.mymovies.domain.entity.Movie

interface MovieRepository {
    suspend fun getMovieList():List<Movie>
}
