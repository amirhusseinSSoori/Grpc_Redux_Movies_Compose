package com.amirhusseinsoori.grpckotlin.ui.movie

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.amirhusseinsoori.common.Constance.NoError
import com.amirhusseinsoori.domain.entity.model.BannerModel
import com.amirhusseinsoori.grpckotlin.component.banner.BannerPager
import com.amirhusseinsoori.grpckotlin.ui.movie.pattern.MovieEffect
import com.amirhusseinsoori.grpckotlin.ui.movie.pattern.MovieViewState



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
            Column() {
                it.value.slider?.let {
                    Banner(it)
                }
                Text(text = it.value.toString(), modifier = Modifier.clickable {
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


}

@Composable
fun Banner(slider:List<BannerModel>) {
    val context = LocalContext.current
    val scroller = rememberScrollState()
    Column(
        modifier = Modifier
            .height(250.dp)
            .verticalScroll(scroller)
    ) {

        BannerPager(
            modifier = Modifier.padding(top = 10.dp),
            items = slider,
            indicatorGravity = Alignment.BottomCenter
        ) { item ->
            Toast.makeText(context, "${item.name}", Toast.LENGTH_SHORT).show()
        }


    }
}


