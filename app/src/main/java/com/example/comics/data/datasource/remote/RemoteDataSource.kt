package com.example.comics.data.datasource.remote

import com.example.comics.data.model.MovieResponse
import com.example.comics.util.domain.DataError
import com.example.comics.util.domain.Result

internal interface RemoteDataSource {
    suspend fun getMovies(page: Int): Result<MovieResponse, DataError.Remote>
}