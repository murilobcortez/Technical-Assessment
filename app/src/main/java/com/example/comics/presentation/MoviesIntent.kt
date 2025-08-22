package com.example.comics.presentation

internal sealed class MoviesIntent {
    object LoadNextPage : MoviesIntent()
}