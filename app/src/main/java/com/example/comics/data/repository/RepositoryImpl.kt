package com.example.comics.data.repository

import com.example.comics.util.domain.DataError
import com.example.comics.util.domain.Result
import com.example.comics.data.mapper.toMovie
import com.example.comics.data.datasource.remote.RemoteDataSource
import com.example.comics.domain.model.Movie
import com.example.comics.domain.repository.Repository
import com.example.comics.util.domain.map

internal class RepositoryImpl(
    private val remoteDataSource: RemoteDataSource
): Repository {

    override suspend fun getMovies(page: Int): Result<Movie, DataError> {
        return remoteDataSource
            .getMovies(page = page)
            .map { movieResponse ->
                movieResponse.toMovie()
            }
    }
}