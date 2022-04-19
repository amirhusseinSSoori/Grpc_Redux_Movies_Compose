package com.amirhusseinsoori.grpckotlin.ui.movie

import android.annotation.SuppressLint
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.amirhusseinsoori.common.Constance.NoError
import com.amirhusseinsoori.grpckotlin.ui.movie.component.Banner
import com.amirhusseinsoori.grpckotlin.ui.movie.pattern.MovieEffect
import com.amirhusseinsoori.grpckotlin.ui.movie.pattern.MovieViewState
import com.amirhusseinsoori.grpckotlin.ui.theme.bCard
import com.amirhusseinsoori.grpckotlin.ui.movie.component.ComedyItems
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
        viewModel.viewState.collectAsState(initial = MovieViewState()).let {
            MovieDetails(it.value.slider,it.value.listMovies)
        }


        viewModel.viewEffect.collectAsState(initial = MovieEffect()).let {
            if (it.value.messageError != NoError) {
                Toast.makeText(context, "${it.value.messageError}", Toast.LENGTH_SHORT).show()
            }
        }
    }


}



