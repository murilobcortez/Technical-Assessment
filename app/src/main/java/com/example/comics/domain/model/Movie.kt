package com.example.comics.domain.model

internal data class Movie(
    val page: Int,
    val totalPages: Int,
    val totalResults: Int,
    val results: List<MovieItem>
)

data class MovieItem(
    val title: String?,
    val overview: String?,
    val posterPath: String?
)