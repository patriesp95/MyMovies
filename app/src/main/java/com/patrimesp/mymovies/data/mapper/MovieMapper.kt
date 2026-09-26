package com.patrimesp.mymovies.data.mapper

import com.patrimesp.mymovies.data.response.list.MovieItemResponse
import com.patrimesp.mymovies.data.response.detail.MovieDetailResponse
import com.patrimesp.mymovies.domain.entity.Movie

fun MovieItemResponse.toDomain(): Movie {
    return Movie(
        id = id,
        adult = adult,
        overview = overview,
        popularity = popularity,
        posterPath = posterPath,
        releaseDate = releaseDate,
        title = title
    )
}

fun MovieDetailResponse.toDomain(): Movie {
    return Movie(
        id = id,
        adult = adult,
        overview = overview,
        popularity = popularity,
        posterPath = posterPath,
        releaseDate = releaseDate,
        title = title,
        runtime = runtime,
        voteAverage = voteAverage,
        genres = genres.map { genre -> genre.name }
    )
}
