package com.amirhusseinsoori.grpckotlin.ui.movie.component

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.amirhusseinsoori.domain.entity.DomainMoviesItem
import com.amirhusseinsoori.domain.entity.model.BannerModel
import com.amirhusseinsoori.grpckotlin.component.banner.BannerPager
import com.amirhusseinsoori.grpckotlin.component.banner.utils.ProvideGradiant


@Composable
fun MovieDetails(
    itemsBanner: List<BannerModel>?,
    famousItems: List<DomainMoviesItem>,
    serialsItems: List<DomainMoviesItem>,
    comedyItems: List<DomainMoviesItem>,
    searchList: List<DomainMoviesItem>
) {
    Banner(itemsBanner)

    MovieList(
        "Comedy & Action",
        comedyItems, ProvideGradiant.BlackAndWhite.list
    )

    MovieList(
        "Serials",
        serialsItems,
        ProvideGradiant.WhiteAndLightBack.list
    )


    MovieList(
        "Popular",
        famousItems,
        ProvideGradiant.WhiteAndBlack.list

    )


}


@Composable
fun Banner(
    slider: List<BannerModel>?,
    scroller: ScrollState = rememberScrollState(),
    context: Context = LocalContext.current
) {
    Column(
        modifier = Modifier
            .height(250.dp)
            .verticalScroll(scroller)
    ) {
        BannerPager(
            modifier = Modifier.padding(top = 10.dp),
            items = slider ?: emptyList(),
            indicatorGravity = Alignment.BottomCenter
        ) { item ->
            Toast.makeText(context, "${item.Name}", Toast.LENGTH_SHORT).show()
        }
    }
}



