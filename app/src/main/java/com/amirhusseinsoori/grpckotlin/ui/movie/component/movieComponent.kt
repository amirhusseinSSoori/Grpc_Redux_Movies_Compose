package com.amirhusseinsoori.grpckotlin.ui.movie.component

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.amirhusseinsoori.domain.entity.DomainMoviesItem
import com.amirhusseinsoori.domain.entity.model.BannerModel
import com.amirhusseinsoori.grpckotlin.component.banner.BannerPager
import com.amirhusseinsoori.grpckotlin.component.banner.utils.ProvideGradiant
import com.amirhusseinsoori.grpckotlin.ui.theme.bCard


@Composable
fun MovieDetails(
    itemsBanner: List<BannerModel>?,
    famousItems: List<DomainMoviesItem>,
    comedyItems:List<DomainMoviesItem>
) {
    Column(
        modifier = Modifier
            .verticalScroll(
                state = rememberScrollState())
            .padding(10.dp)
    ) {
        Banner(itemsBanner)
        MovieList(
            comedyItems, ProvideGradiant.PurpleAndWhite.list
        )
        MovieList(
            comedyItems,
            ProvideGradiant.WhiteAndWhite.list
        )
        MovieList(
            famousItems,
            ProvideGradiant.WhiteAndPurple.list

        )

    }
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
            Toast.makeText(context, "${item.name}", Toast.LENGTH_SHORT).show()
        }
    }
}



