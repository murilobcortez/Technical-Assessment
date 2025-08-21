package com.example.comics

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import com.example.comics.presentation.MoviesScreen
import com.example.comics.presentation.MoviesViewModel
import org.koin.androidx.compose.KoinAndroidContext
import org.koin.androidx.compose.koinViewModel

internal class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            KoinAndroidContext {
                AppContent()
            }
        }
    }
}

@Composable
private fun AppContent() {
    MaterialTheme {
        val viewModel: MoviesViewModel = koinViewModel()
        MoviesScreen(viewModel = viewModel)
    }
}