package com.amirhusseinsoori.grpckotlin.ui.movie

import android.annotation.SuppressLint
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.amirhusseinsoori.common.Constance.NoError
import com.amirhusseinsoori.domain.entity.model.BannerModel
import com.amirhusseinsoori.grpckotlin.component.banner.BannerPager
import com.amirhusseinsoori.grpckotlin.ui.movie.pattern.MovieEffect
import com.amirhusseinsoori.grpckotlin.ui.movie.pattern.MovieViewState
import com.amirhusseinsoori.grpckotlin.ui.theme.bCard
import com.example.movieapp.ui.component.MovieListItem


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
                    Log.e("Movie", "Movie: ${it}")
                    Banner(it)
                }
                Spacer(modifier = Modifier.padding(top = 15.dp))
//                Text(text = it.value.toString(), modifier = Modifier)
                Card(
                    shape = RoundedCornerShape(corner = CornerSize(10.dp)),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(300.dp)
                        .background(
                            shape = RoundedCornerShape(corner = CornerSize(10.dp)),
                            brush = Brush.verticalGradient(
                                colors = listOf(
                                    Color.White,
                                    bCard
                                )
                            )
                        )
                        .padding(8.dp),
                ) {
                    Text(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(
                                top = 5.dp,
                                start = 18.dp
                            ),
                        text = "Comedy & Action",
                        style = MaterialTheme.typography.h6,
                        textAlign = TextAlign.Left,
                        color = Color.Black,
                        fontSize = 18.sp
                    )
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(5.dp)
                    ) {

                        LazyRow(

                        ) {
                            items(it.value.listMovies){
                                MovieListItem(it)
                            }
                        }
                    }
                }

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
fun Banner(slider: List<BannerModel>) {
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


