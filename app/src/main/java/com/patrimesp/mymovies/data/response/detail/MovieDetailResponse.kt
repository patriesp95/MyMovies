package com.patrimesp.mymovies.data.response.detail

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MovieDetailResponse(
    val id: Int,
    val adult: Boolean,
    val overview: String,
    val popularity: Double,
    @SerialName("poster_path") val posterPath: String? = null,
    @SerialName("backdrop_path") val backdropPath: String? = null,
    @SerialName("release_date") val releaseDate: String,
    val title: String,
    val runtime: Int? = null,
    @SerialName("vote_average") val voteAverage: Double,
    val genres: List<GenreResponse> = emptyList()
)

@Serializable
data class GenreResponse(
    val id: Int,
    val name: String
)
