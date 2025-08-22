package com.example.comics.domain.usecase

import com.example.comics.domain.repository.Repository
import com.example.comics.util.domain.DataError
import com.example.comics.util.domain.Result
import com.example.comics.domain.usecase.stub.TestStub
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.junit.Assert.assertEquals
import org.mockito.Mockito.mock
import org.mockito.kotlin.whenever

class GetMoviesUseCaseUnitTest {

    private lateinit var repository: Repository
    private lateinit var useCase: GetMoviesUseCase

    @Before
    fun setUp() {
        repository = mock()
        useCase = GetMoviesUseCase(repository)
    }

    @Test
    fun `should return list of movies when repository returns success`() = runBlocking {
        // Given
        val movies = TestStub.generateMovies()
        val page = 1
        whenever(repository.getMovies(page)).thenReturn(Result.Success(movies))

        // When
        val result = useCase.invoke(page)

        // Then
        assertEquals(Result.Success(movies), result)
    }

    @Test
    fun `should return error when repository returns request timeout`() = runBlocking {
        // Given
        val error = DataError.Remote.REQUEST_TIMEOUT
        val page = 1
        whenever(repository.getMovies(page)).thenReturn(Result.Error(error))

        // When
        val result = useCase.invoke(page)

        // Then
        assertEquals(Result.Error(error), result)
    }
}