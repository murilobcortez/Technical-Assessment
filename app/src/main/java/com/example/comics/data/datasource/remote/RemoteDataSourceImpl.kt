package com.example.comics.data.datasource.remote

import com.example.comics.data.datasource.remote.service.Service
import com.example.comics.data.model.MovieResponse
import com.example.comics.util.data.safeRun
import com.example.comics.util.domain.DataError
import com.example.comics.util.domain.Result

internal class RemoteDataSourceImpl(
    private val service: Service
): RemoteDataSource {

    override suspend fun getMovies(page: Int): Result<MovieResponse, DataError.Remote> {
        return safeRun {
            service.getMovies(page = page)
        }
    }
}