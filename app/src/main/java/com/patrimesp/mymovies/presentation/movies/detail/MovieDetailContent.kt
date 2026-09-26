package com.patrimesp.mymovies.presentation.movies.detail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.patrimesp.mymovies.domain.entity.Movie

@Composable
internal fun MovieDetailContent(
    movie: Movie,
    navigateBack: () -> Unit
) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        LazyColumn(modifier = Modifier.fillMaxSize()) {
            item {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .aspectRatio(16f / 10f)
                        .background(MaterialTheme.colorScheme.surfaceVariant)
                ) {
                    val imagePath = movie.posterPath
                    if (imagePath != null) {
                        AsyncImage(
                            model = "https://image.tmdb.org/t/p/w780$imagePath",
                            contentDescription = "Imagen de ${movie.title}",
                            contentScale = ContentScale.Crop,
                            modifier = Modifier.fillMaxSize()
                        )
                    }

                    Surface(
                        shape = RoundedCornerShape(24.dp),
                        color = MaterialTheme.colorScheme.surface.copy(alpha = 0.88f),
                        modifier = Modifier
                            .align(Alignment.TopStart)
                            .padding(12.dp)
                    ) {
                        TextButton(onClick = navigateBack) {
                            Text("← Volver")
                        }
                    }
                }
            }

            item {
                Column(
                    verticalArrangement = Arrangement.spacedBy(14.dp),
                    modifier = Modifier.padding(20.dp)
                ) {
                    Text(
                        text = movie.title,
                        style = MaterialTheme.typography.headlineMedium
                    )

                    Row(
                        horizontalArrangement = Arrangement.spacedBy(16.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        if (movie.releaseDate.isNotBlank()) {
                            Text(
                                text = movie.releaseDate.take(4),
                                style = MaterialTheme.typography.bodyMedium
                            )
                        }
                        movie.runtime?.let { runtime ->
                            Text(
                                text = "${runtime / 60} h ${runtime % 60} min",
                                style = MaterialTheme.typography.bodyMedium
                            )
                        }
                        Text(
                            text = "★ ${"%.1f".format(movie.voteAverage)}",
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.primary
                        )
                    }

                    if (movie.genres.isNotEmpty()) {
                        Text(
                            text = movie.genres.joinToString(" · "),
                            style = MaterialTheme.typography.labelLarge,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }

                    Text(
                        text = "Sinopsis",
                        style = MaterialTheme.typography.titleMedium
                    )
                    Text(
                        text = movie.overview.ifBlank { "Sin sinopsis disponible." },
                        style = MaterialTheme.typography.bodyLarge
                    )
                }
            }
        }
    }
}
