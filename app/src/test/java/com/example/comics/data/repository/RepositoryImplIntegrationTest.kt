package com.example.comics.data.repository

import com.example.comics.util.domain.DataError
import com.example.comics.util.domain.Result
import com.example.comics.data.datasource.remote.RemoteDataSourceImpl
import com.example.comics.data.datasource.remote.service.Service
import org.junit.rules.ErrorCollector
import com.example.comics.data.repository.stub.ServiceStub
import kotlinx.coroutines.runBlocking
import org.junit.Rule
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever
import org.junit.Assert.assertEquals
import org.hamcrest.CoreMatchers.equalTo

class RepositoryImplIntegrationTest {

    private fun createRepository(
        service: Service = mock()
    ) = RepositoryImpl(
        RemoteDataSourceImpl(
            service
        )
    )

    @get:Rule
    val collector = ErrorCollector()

    @Test
    fun `should map movies correctly from successfully stubbed json file`() = runBlocking {
        // Given
        val repository = createRepository(
            ServiceStub("src/androidTest/res/raw/movies_stub_success_response.json")
        )
        val page = 1

        // When
        val result = repository.getMovies(page)

        // Then
        when (result) {
            is Result.Success -> {
                collector.checkThat(result.data.page, equalTo(1))
                collector.checkThat(result.data.totalPages, equalTo(2))
            }
            else -> error("Expected result success but got $result")
        }
    }

    @Test
    fun `should return unknown error when API service throws exception`() = runBlocking {
        // Given
        val stubbedApiService = mock<Service>()
        val page = 1
        val authorization = "Bearer fake"
        whenever(stubbedApiService.getMovies(authorization, page)).thenThrow(RuntimeException())
        val repository = createRepository(stubbedApiService)

        // When
        val result = repository.getMovies(page)

        // Then
        when (result) {
            is Result.Error -> assertEquals(DataError.Remote.UNKNOWN, result.error)
            else -> error("Expected result unknown error but got $result")
        }
    }
}