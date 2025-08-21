package com.example.comics.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.comics.domain.usecase.GetMoviesUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import com.example.comics.util.domain.onError
import com.example.comics.util.domain.onSuccess
import com.example.comics.util.presentation.toUiText
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.update

private const val FIRST_PAGE = 1

internal class MoviesViewModel(
    private val getMoviesUseCase: GetMoviesUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(MoviesState())
    val state: StateFlow<MoviesState> = _state

    init {
        loadPage(FIRST_PAGE)
    }

    fun handleIntent(intent: MoviesIntent) {
        when (intent) {
            is MoviesIntent.LoadNextPage -> {
                if (!_state.value.isLoading && _state.value.currentPage < _state.value.totalPages) {
                    loadPage(_state.value.currentPage + 1)
                }
            }
        }
    }

    private fun loadPage(page: Int) {
        //CHECAR O DISPATCHER
        viewModelScope.launch(Dispatchers.IO) {
            _state.update {
                it.copy(
                    isLoading = true,
                    error = null
                )
            }

            getMoviesUseCase(page)
                .onSuccess {
                    val newMovies = _state.value.movies + it.results

                    _state.value = _state.value.copy(
                        movies = newMovies,
                        currentPage = it.page,
                        totalPages = it.totalPages,
                        isLoading = false,
                        error = null
                    )
                }
                .onError { error ->
                    _state.update {
                        it.copy(
                            isLoading = false,
                            error = error.toUiText(),
                        )
                    }
                }
        }
    }
}