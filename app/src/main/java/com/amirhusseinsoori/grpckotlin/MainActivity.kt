package com.amirhusseinsoori.grpckotlin

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.amirhusseinsoori.grpckotlin.ui.movie.Movie
import com.amirhusseinsoori.grpckotlin.ui.movie.MovieViewModel
import com.amirhusseinsoori.grpckotlin.ui.theme.ThemeGrpcMovies
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ThemeGrpcMovies {
                val viewModel: MovieViewModel = hiltViewModel()
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                 Movie(viewModel = viewModel)
                }
            }
        }
    }
}