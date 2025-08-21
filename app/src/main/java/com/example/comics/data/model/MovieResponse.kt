package com.example.comics.data.model

internal data class MovieResponse(
    val page: Int,
    val totalPages: Int,
    val totalResults: Int,
    val results: List<MovieItemResponse>
)

internal data class MovieItemResponse(
    val title: String,
    val overview: String,
    val posterPath: String
)