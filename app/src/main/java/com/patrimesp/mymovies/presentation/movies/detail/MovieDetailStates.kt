package com.patrimesp.mymovies.presentation.movies.detail

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
internal fun MovieDetailLoadingState() {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        CircularProgressIndicator()
    }
}

@Composable
internal fun MovieDetailErrorState(
    message: String,
    navigateBack: () -> Unit
) {
    MovieDetailMessageState(message = message, navigateBack = navigateBack)
}

@Composable
internal fun MovieDetailEmptyState(navigateBack: () -> Unit) {
    MovieDetailMessageState(
        message = "No se ha encontrado la película.",
        navigateBack = navigateBack
    )
}

@Composable
private fun MovieDetailMessageState(
    message: String,
    navigateBack: () -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(12.dp, Alignment.CenterVertically),
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp)
    ) {
        Text(
            text = message,
            style = MaterialTheme.typography.bodyLarge
        )
        TextButton(onClick = navigateBack) {
            Text("Volver")
        }
    }
}
