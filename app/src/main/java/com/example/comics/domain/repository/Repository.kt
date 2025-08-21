package com.example.comics.domain.repository

import com.example.comics.util.domain.Result
import com.example.comics.util.domain.DataError
import com.example.comics.domain.model.Movie

internal interface Repository {
    suspend fun getMovies(page: Int): Result<Movie, DataError>
}