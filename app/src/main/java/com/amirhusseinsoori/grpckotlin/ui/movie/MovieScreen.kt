package com.amirhusseinsoori.grpckotlin.ui.movie

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.platform.LocalContext
import com.amirhusseinsoori.common.Constance.NoError
import com.amirhusseinsoori.grpckotlin.ui.ShowErrorDialog
import com.amirhusseinsoori.grpckotlin.ui.ShowLoading
import com.amirhusseinsoori.grpckotlin.ui.movie.component.MovieDetails
import com.amirhusseinsoori.grpckotlin.ui.movie.pattern.MovieEffect
import com.amirhusseinsoori.grpckotlin.ui.movie.pattern.MovieViewState


@SuppressLint("RememberReturnType")
@Composable
fun Movie(viewModel: MovieViewModel) {
    Scaffold(topBar = {
        TopAppBar(
            title = { Text(text = "Movie with Grpc") }
        )
    }) {
        viewModel.viewState.collectAsState(initial = MovieViewState()).let { result ->
            result.value.apply {
                if (slider!!.isNotEmpty()) {
                    MovieDetails(slider, listFamous, listSerials, listComedy)
                }
                ShowErrorDialog(showDialog = DiaLog, message, callEvent = {
                    viewModel.callEvent()
                })
            }
        }
        viewModel.viewEffect.collectAsState(initial = MovieEffect()).let {
            it.value.apply {
                ShowLoading(showProgressBar)
            }
        }

    }


}



