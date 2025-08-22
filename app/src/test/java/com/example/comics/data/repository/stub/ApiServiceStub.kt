package com.example.comics.data.repository.stub

import com.example.comics.data.datasource.remote.service.Service
import com.example.comics.data.model.MovieResponse
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import retrofit2.Response
import java.io.File

internal class ServiceStub(val jsonResponsePathFile: String) : Service {
    override suspend fun getMovies(
        authHeader: String,
        page: Int
    ): Response<MovieResponse> {
        return try {
            val json = File(jsonResponsePathFile).readText()
            val gson = Gson()
            val listType = object : TypeToken<MovieResponse>() {}.type
            val result: MovieResponse = gson.fromJson(json, listType)
            return Response.success(result)
        } catch (e: Exception) {
            Response.error(500, okhttp3.ResponseBody.create(null, "Unknown Error"))
        }
    }
}