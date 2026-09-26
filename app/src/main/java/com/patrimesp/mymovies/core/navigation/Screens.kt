package com.patrimesp.mymovies.core.navigation

import kotlinx.serialization.Serializable

@Serializable
object MovieList

@Serializable
data class MovieDetail(val movieId: Int)
