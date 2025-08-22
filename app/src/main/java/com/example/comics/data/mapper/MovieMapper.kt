package com.example.comics.data.mapper

import com.example.comics.data.model.MovieItemResponse
import com.example.comics.data.model.MovieResponse
import com.example.comics.domain.model.Movie
import com.example.comics.domain.model.MovieItem

internal fun MovieResponse.toMovie(): Movie {
    return Movie(
        page = page,
        totalPages = totalPages,
        results = results.map { it.toMovieItem() }
    )
}

internal fun MovieItemResponse.toMovieItem(): MovieItem {
    return MovieItem(
        title = title,
        overview = overview,
        posterPath = posterPath
    )
}