package com.patrimesp.mymovies.domain.entity

import kotlinx.serialization.Serializable

@Serializable
data class Movie(
    val id: Int,
    val adult: Boolean,
    val overview: String,
    val popularity: Double,
    val posterPath: String? = null,
    val releaseDate: String,
    val title: String,
)