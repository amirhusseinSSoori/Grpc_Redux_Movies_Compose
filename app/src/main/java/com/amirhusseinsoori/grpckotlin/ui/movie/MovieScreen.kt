package com.amirhusseinsoori.grpckotlin.ui.movie

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState


@Composable
fun Movie(viewModel: MovieViewModel) {

    viewModel._state.collectAsState().let {


        Column() {
            Text(text = it.value)
        }
    }

}