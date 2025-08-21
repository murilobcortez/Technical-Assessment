package com.example.comics.presentation

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.comics.presentation.components.MovieItemComponent

@Composable
internal fun MoviesScreen(viewModel: MoviesViewModel) {
    val state by viewModel.state.collectAsState()

    Box(modifier = Modifier.fillMaxSize()) {
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(8.dp)
        ) {
            itemsIndexed(state.movies) { index, movie ->
                MovieItemComponent(movie = movie)
                Spacer(modifier = Modifier.height(8.dp))

                if (index >= state.movies.size - 5) {
                    LaunchedEffect(Unit) {
                        viewModel.handleIntent(MoviesIntent.LoadNextPage)
                    }
                }
            }
        }

        if (state.isLoading) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        }

        state.error?.let {
            Text(
                text = it.asString()
            )
        }
    }
}