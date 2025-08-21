package com.example.comics.domain.usecase

import com.example.comics.domain.model.Movie
import com.example.comics.domain.repository.Repository
import com.example.comics.util.domain.DataError
import com.example.comics.util.domain.Result

internal class GetMoviesUseCase(
    private val repository: Repository
) {
    suspend operator fun invoke(page: Int): Result<Movie, DataError>{
        return repository.getMovies(page = page)
    }
}