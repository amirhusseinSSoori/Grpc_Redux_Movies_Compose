package com.amirhusseinsoori.grpckotlin.ui.search

import SearchBar
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.amirhusseinsoori.grpckotlin.component.banner.utils.lifecycleOwnerTools
import com.amirhusseinsoori.grpckotlin.ui.MovieViewModel
import com.amirhusseinsoori.grpckotlin.ui.ShowErrorDialog
import com.amirhusseinsoori.grpckotlin.ui.ShowLoading
import com.amirhusseinsoori.grpckotlin.ui.movie.pattern.MovieEffect
import com.amirhusseinsoori.grpckotlin.ui.movie.pattern.MovieViewState
import com.amirhusseinsoori.grpckotlin.ui.search.component.SearchItem


@OptIn(ExperimentalComposeUiApi::class, ExperimentalAnimationApi::class)
@Composable
fun Search(viewModel: MovieViewModel, navController: NavController) {


    Column(
        modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        var expanded by remember { mutableStateOf(false) }
        var value by remember { mutableStateOf("") }
        val state by lifecycleOwnerTools(viewModel.viewState).collectAsState(MovieViewState())

        Surface() {

            Column(modifier = Modifier.fillMaxSize()) {
                SearchBar(
                    query = value,
                    onQueryChange = {
                        value = it
                        viewModel.searchMovies(value)
                    },
                    onSearchFocusChange = { expanded = false },
                    onClearQuery = {
                        value = ""
                    },
                    enableClose = value != "",
                )
                state.let { result ->
                    result.apply {
                        LazyColumn {
                            items(result.searchList) { item ->
                                SearchItem(movie = item, navController = navController)
                            }
                        }

                        ShowErrorDialog(showDialog = DiaLog, message, callEvent = {
                            viewModel.callEvent()
                        })
                    }


                }
            }

            viewModel.viewEffect.collectAsState(initial = MovieEffect()).let {
                it.value.apply {
                    ShowLoading(showProgressBar)
                }
            }
        }

    }

}