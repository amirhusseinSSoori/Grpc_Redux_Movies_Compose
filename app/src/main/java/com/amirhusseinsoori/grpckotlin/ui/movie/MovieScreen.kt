package com.amirhusseinsoori.grpckotlin.ui.movie

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.platform.LocalContext


@Composable
fun Movie(viewModel: MovieViewModel) {
    val context = LocalContext.current
    viewModel.viewState.collectAsState().let {

        Column() {
            Text(text = it.value.toString())
        }



    }
    viewModel.viewEffect.collectAsState(initial = MovieEffect()).let {
        if (it.value.messageError != null) {
            Toast.makeText(context, "${it.value.messageError}", Toast.LENGTH_SHORT).show()
        }




    }

}


