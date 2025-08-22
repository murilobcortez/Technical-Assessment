package com.example.comics.data.model

import com.google.gson.annotations.SerializedName

internal data class MovieResponse(
    val page: Int,
    @SerializedName("total_pages") val totalPages: Int,
    val results: List<MovieItemResponse>
)

internal data class MovieItemResponse(
    val title: String? = null,
    val overview: String? = null,
    @SerializedName("poster_path") val posterPath: String? = null
)