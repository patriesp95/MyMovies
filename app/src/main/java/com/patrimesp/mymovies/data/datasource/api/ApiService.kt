package com.patrimesp.mymovies.data.datasource.api

import com.patrimesp.mymovies.data.response.list.MovieResponse
import retrofit2.http.GET

interface ApiService {
    @GET("popular?language=en-US&page=1")
    suspend fun getMovies(): MovieResponse
}
