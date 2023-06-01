package com.amirhusseinsoori.grpckotlin.ui.movie.component

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavController
import com.amirhusseinsoori.domain.entity.DomainMoviesItem
import com.amirhusseinsoori.domain.entity.model.BannerModel
import com.amirhusseinsoori.grpckotlin.component.banner.BannerPager
import com.amirhusseinsoori.grpckotlin.component.banner.utils.ProvideGradiant
import com.amirhusseinsoori.grpckotlin.component.banner.utils.utilFont



@Composable
fun MovieDetails(
    itemsBanner: List<BannerModel>?,
    famousItems: List<DomainMoviesItem>,
    serialsItems: List<DomainMoviesItem>,
    comedyItems: List<DomainMoviesItem>,
    navController: NavController
) {
    Banner(itemsBanner)

    MovieList(
        "Comedy & Action",
        comedyItems, ProvideGradiant.BlackAndWhite.list,
        navController
    )

    MovieList(
        "Serials",
        serialsItems,
        ProvideGradiant.WhiteAndLightBack.list,
        navController
    )


    MovieList(
        "Popular",
        famousItems,
        ProvideGradiant.WhiteAndBlack.list,
        navController
    )


}

@Composable
fun MovieToolbar(onClick:()->Unit) {
    ConstraintLayout(modifier = Modifier.fillMaxSize()) {
        val (icon, txt) = createRefs()
        Icon(
            modifier = Modifier.clickable { onClick() }.constrainAs(icon) {
                top.linkTo(parent.top, margin = 4.dp)
                end.linkTo(parent.end, margin = 16.dp)
                bottom.linkTo(parent.bottom, margin = 4.dp)
            },
            imageVector = Icons.Outlined.Search,
            tint = Color.White,
            contentDescription = "search"
        )

        Text(
            modifier = Modifier.constrainAs(txt) {
                top.linkTo(parent.top)
                start.linkTo(parent.start)
                bottom.linkTo(parent.bottom)
            }, fontFamily = utilFont,
            fontWeight = FontWeight.Normal,
            text = "Movie with Grpc",
            color = Color.White
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
        ) {
           //do someThing
        }
    }
}



