package com.example.comics.domain.usecase.stub

import com.example.comics.domain.model.Movie
import com.example.comics.domain.model.MovieItem

object TestStub {
    internal fun generateMovies(): Movie {
        return Movie(
            page = 1,
            totalPages = 1,
            results = generateMovieItemList()
        )
    }

    internal fun generateMovieItemList(): List<MovieItem> {
        return listOf(
            MovieItem(
                title = "Fight Club",
                overview = "You should not talk about fight club",
                posterPath = "https://fake.url"
            ),
            MovieItem(
                title = "Fight Club",
                overview = "You should not talk about fight club",
                posterPath = "https://fake.url"
            )
        )
    }
}