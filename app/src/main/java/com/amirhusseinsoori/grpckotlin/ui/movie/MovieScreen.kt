package com.amirhusseinsoori.grpckotlin.ui.movie

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.amirhusseinsoori.grpckotlin.ui.ShowErrorDialog
import com.amirhusseinsoori.grpckotlin.ui.ShowLoading
import com.amirhusseinsoori.grpckotlin.ui.movie.component.MovieDetails
import com.amirhusseinsoori.grpckotlin.ui.movie.pattern.MovieEffect
import com.amirhusseinsoori.grpckotlin.ui.movie.pattern.MovieViewState
import kotlin.math.log


@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun Movie(viewModel: MovieViewModel) {
    Scaffold(topBar = {
        TopAppBar(
            title = { Text(text = "Movie with Grpc") }
        )
    }) { padding ->
        viewModel.viewState.collectAsState(initial = MovieViewState()).let { result ->
            result.value.apply {


                if (slider!!.isNotEmpty()) {
                    Column(
                        modifier = Modifier
                            .verticalScroll(
                                state = rememberScrollState()
                            )
                            .padding(
                                start = 5.dp,
                                end = 5.dp,
                                top = padding.calculateTopPadding()
                            )
                    ) {
                        MovieDetails(
                            slider,
                            listFamous,
                            listSerials,
                            listComedy,
                            searchList
                        )
                    }
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




