package com.amirhusseinsoori.grpckotlin.ui.movie

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.platform.LocalContext
import com.amirhusseinsoori.common.Constance.NoError
import com.amirhusseinsoori.grpckotlin.ui.movie.pattern.MovieEffect
import com.amirhusseinsoori.grpckotlin.ui.movie.pattern.MovieViewState
import com.amirhusseinsoori.grpckotlin.ui.movie.component.MovieDetails


@SuppressLint("RememberReturnType")
@Composable
fun Movie(viewModel: MovieViewModel) {
    val context = LocalContext.current

    Scaffold(topBar = {
        TopAppBar(
            title = { Text(text = "Movie with Grpc") }
        )
    }) {
        viewModel.viewState.collectAsState(initial = MovieViewState()).let {rsualt ->
            rsualt.value.apply {
                MovieDetails(slider,listMovies)
            }
        }
        viewModel.viewEffect.collectAsState(initial = MovieEffect()).let {
            if (it.value.messageError != NoError) {
                Toast.makeText(context, "${it.value.messageError}", Toast.LENGTH_SHORT).show()
            }
        }
    }


}



