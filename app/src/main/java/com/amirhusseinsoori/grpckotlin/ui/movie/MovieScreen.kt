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
    val context = LocalContext.current
    var processErrorDialog by rememberSaveable {
        mutableStateOf(false)
    }
    var messageErrorDialog by rememberSaveable {
        mutableStateOf("")
    }
    var loading by rememberSaveable {
        mutableStateOf(false)
    }
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
                processErrorDialog=DiaLog
                messageErrorDialog=message
                Log.e("slider", "Movie:${listFamous} ")
            }
        }


        viewModel.viewEffect.collectAsState(initial = MovieEffect()).let {
            it.value.apply {

                loading = showProgressBar

            }
        }


        ShowErrorDialog(showDialog = processErrorDialog, messageErrorDialog, callEvent = {
            viewModel.callEvent()
        })
        ShowLoading(loading)
    }


}



