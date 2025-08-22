package com.example.comics.domain.model

internal data class Movie(
    val page: Int,
    val totalPages: Int,
    val results: List<MovieItem>
)

internal data class MovieItem(
    val title: String?,
    val overview: String?,
    val posterPath: String?
)