package com.example.comics.presentation

sealed class MoviesIntent {
    object LoadNextPage : MoviesIntent()
}