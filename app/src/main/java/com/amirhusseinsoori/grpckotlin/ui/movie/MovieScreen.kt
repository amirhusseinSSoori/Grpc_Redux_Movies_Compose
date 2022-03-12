package com.amirhusseinsoori.grpckotlin.ui.movie

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.lifecycle.Lifecycle
import com.amirhusseinsoori.common.Constance
import com.amirhusseinsoori.common.Constance.NoError
import com.amirhusseinsoori.grpckotlin.ui.movie.pattern.MovieAction
import com.amirhusseinsoori.grpckotlin.ui.movie.pattern.MovieEffect
import com.amirhusseinsoori.grpckotlin.ui.movie.pattern.MovieViewState
import kotlinx.coroutines.flow.MutableStateFlow


@SuppressLint("RememberReturnType")
@Composable
fun Movie(viewModel: MovieViewModel) {
    val context = LocalContext.current
    val lifecycleOwner = LocalLifecycleOwner.current


    viewModel.viewState.collectAsState(initial = MovieViewState()).let {

        Column() {
            Text(text = it.value.toString(),modifier = Modifier.clickable {
                viewModel.callEvent()
            })
        }



    }
    viewModel.viewEffect.collectAsState(initial = MovieEffect()).let {

        if (it.value.messageError != NoError) {
            Toast.makeText(context, "${it.value.messageError}", Toast.LENGTH_SHORT).show()
        }




    }

}


