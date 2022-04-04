package com.amirhusseinsoori.grpckotlin.ui.movie

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Lifecycle
import com.amirhusseinsoori.common.Constance
import com.amirhusseinsoori.common.Constance.NoError
import com.amirhusseinsoori.grpckotlin.component.banner.BannerPager
import com.amirhusseinsoori.grpckotlin.component.banner.model.BaseBannerBean
import com.amirhusseinsoori.grpckotlin.ui.movie.pattern.MovieAction
import com.amirhusseinsoori.grpckotlin.ui.movie.pattern.MovieEffect
import com.amirhusseinsoori.grpckotlin.ui.movie.pattern.MovieViewState
import kotlinx.coroutines.flow.MutableStateFlow


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
                Banner()
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

data class BannerBean(
    override val data: Any? = null
) : BaseBannerBean()

@Composable
fun Banner() {
    val context = LocalContext.current
    val scroller = rememberScrollState()
    Column(
        modifier = Modifier
            .verticalScroll(scroller)
    ) {


        val items = arrayListOf(
            BannerBean(
                "https://www.soctex.com/wp-content/uploads/2021/07/John-Wick-4-Wallpaper.jpg",
            ),
            BannerBean(
                "https://www.soctex.com/wp-content/uploads/2021/07/769a02295446964bced55957c4619695-2048x1152.jpg",
            ),
            BannerBean(
                "https://images.thedirect.com/media/article_full/spider-man-box_q1yKDfD.jpg",
            ),
            BannerBean(
                "https://czasostrefa.pl/wp-content/uploads/2021/10/no-time-to-die-movie-poster-banner-01-700x400-1.jpg",
            ),
        )


        BannerPager(
            modifier = Modifier.padding(top = 10.dp),
            items = items,
            indicatorGravity = Alignment.BottomCenter
        ) { item ->
            Toast.makeText(context, "item:$item", Toast.LENGTH_SHORT).show()
        }


    }
}


