package com.amirhusseinsoori.grpckotlin.ui.movie

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.amirhusseinsoori.grpckotlin.component.banner.utils.lifecycleOwnerTools
import com.amirhusseinsoori.grpckotlin.navigation.NavRoute
import com.amirhusseinsoori.grpckotlin.ui.MovieViewModel
import com.amirhusseinsoori.grpckotlin.ui.ShowErrorDialog
import com.amirhusseinsoori.grpckotlin.ui.ShowLoading
import com.amirhusseinsoori.grpckotlin.ui.movie.component.MovieDetails
import com.amirhusseinsoori.grpckotlin.ui.movie.component.MovieToolbar
import com.amirhusseinsoori.grpckotlin.ui.movie.pattern.MovieEffect
import com.amirhusseinsoori.grpckotlin.ui.movie.pattern.MovieViewState
import kotlinx.coroutines.flow.Flow
import kotlin.math.log


@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun Movie(viewModel: MovieViewModel, navController: NavController) {
    Scaffold(topBar = {
        TopAppBar(
            title = {
                MovieToolbar(onClick = {
                    navController.navigate(NavRoute.SearchRoute.route)
                })
            }
        )
    }) { padding ->

        val state by lifecycleOwnerTools(viewModel.viewState).collectAsState(MovieViewState())
        state.let { result ->
            result.apply {
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
                            navController
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






