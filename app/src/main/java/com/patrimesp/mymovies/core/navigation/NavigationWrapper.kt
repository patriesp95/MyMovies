package com.patrimesp.mymovies.core.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.patrimesp.mymovies.presentation.movies.detail.MovieDetailScreen
import com.patrimesp.mymovies.presentation.movies.list.MovieListScreen

@Composable
fun NavigationWrapper() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = MovieList) {
        composable<MovieList> {
            MovieListScreen(navigateToDetail = { navController.navigate(MovieDetail) })
        }

        composable<MovieDetail> {
            MovieDetailScreen(navigateBack = { navController.popBackStack() })
        }
    }


}