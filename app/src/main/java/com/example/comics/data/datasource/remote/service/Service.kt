package com.example.comics.data.datasource.remote.service

import com.example.comics.data.model.MovieResponse
import com.example.comics.util.data.Constants.MOVIES_API_AUTH_HEADER
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

private const val MOVIES_ENDPOINT = "trending/movie/day"

internal interface Service {
    @GET(MOVIES_ENDPOINT)
    suspend fun getMovies(
        @Header("Authorization") authHeader: String = MOVIES_API_AUTH_HEADER,
        @Query("page") page: Int
    ): Response<MovieResponse>
}