package com.example.comics.presentation

import com.example.comics.domain.model.MovieItem
import com.example.comics.util.presentation.UiText

internal data class MoviesState(
    val movies: List<MovieItem> = emptyList(),
    val currentPage: Int = 1,
    val totalPages: Int = 1,
    val isLoading: Boolean = false,
    val error: UiText? = null
)