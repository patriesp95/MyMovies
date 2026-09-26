package com.patrimesp.mymovies.data.datasource.api

import com.patrimesp.mymovies.data.response.list.MovieResponse
import com.patrimesp.mymovies.data.response.detail.MovieDetailResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET("popular?language=en-US&page=1")
    suspend fun getMovies(): MovieResponse

    @GET("{movieId}?language=es-ES")
    suspend fun getMovieDetail(@Path("movieId") movieId: Int): MovieDetailResponse
}
