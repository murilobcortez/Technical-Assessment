package com.example.comics.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import com.example.comics.domain.model.MovieItem
import coil.compose.rememberAsyncImagePainter
import com.example.comics.R.drawable.ic_image_not_found

private const val IMAGE_BASE_URL = "https://image.tmdb.org/t/p/w92"
private const val TITLE_NOT_FOUND_TEXT = "Title Not Founf"
private const val OVERVIEW_NOT_FOUND_TEXT = "Overview Not Found"

@Composable
fun MovieItemComponent(movie: MovieItem) {
    Row(modifier = Modifier.fillMaxWidth()) {

        if(movie.posterPath != null) {
            val posterUrl = IMAGE_BASE_URL + movie.posterPath

            Image(
                painter = rememberAsyncImagePainter(posterUrl),
                contentDescription = movie.title,
                modifier = Modifier
                    .width(92.dp)
                    .height(138.dp),
                contentScale = ContentScale.Crop
            )
        } else {
            Image(
                painter = rememberAsyncImagePainter(ic_image_not_found),
                contentDescription = movie.title,
                modifier = Modifier
                    .width(92.dp)
                    .height(138.dp),
                contentScale = ContentScale.Crop
            )
        }

        Spacer(modifier = Modifier.width(8.dp))
        Column(modifier = Modifier.fillMaxWidth()) {
            Text(
                text = movie.title ?: TITLE_NOT_FOUND_TEXT,
                style = MaterialTheme.typography.titleMedium
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = movie.overview ?: OVERVIEW_NOT_FOUND_TEXT,
                style = MaterialTheme.typography.bodyMedium,
                maxLines = 4,
                overflow = androidx.compose.ui.text.style.TextOverflow.Ellipsis
            )
        }
    }
}