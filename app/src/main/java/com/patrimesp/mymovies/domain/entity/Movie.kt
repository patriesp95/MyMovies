package com.patrimesp.mymovies.domain.entity

import kotlinx.serialization.Serializable

@Serializable
data class Movie(
    val id: Int,
    val adult: Boolean,
    val overview: String,
    val popularity: Double,
    val posterPath: String? = null,
    val backdropPath: String? = null,
    val releaseDate: String,
    val title: String,
    val runtime: Int? = null,
    val voteAverage: Double = 0.0,
    val genres: List<String> = emptyList()
)
