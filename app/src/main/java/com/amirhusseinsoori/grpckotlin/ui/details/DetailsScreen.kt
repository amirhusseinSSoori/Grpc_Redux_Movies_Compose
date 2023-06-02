package com.amirhusseinsoori.grpckotlin.ui.details


import androidx.compose.animation.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.*
import androidx.compose.ui.draw.blur
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import coil.compose.rememberImagePainter
import coil.size.OriginalSize
import com.amirhusseinsoori.domain.entity.DomainMoviesItem
import com.amirhusseinsoori.grpckotlin.R
import com.amirhusseinsoori.grpckotlin.component.banner.utils.utilFont
import com.amirhusseinsoori.grpckotlin.ui.theme.hoverColor
import kotlinx.coroutines.delay


@Composable
fun ScreenDetails(movieItems: DomainMoviesItem) {
    ConstraintLayout(Modifier.fillMaxSize()) {
        DetailsImage(uri = movieItems.Picture)
        HoverForGround(movieItems)

    }
}

@Composable
private fun HoverForGround(movieItems: DomainMoviesItem) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(hoverColor)
    ) {
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 20.dp),
            fontFamily = utilFont,
            fontWeight = FontWeight.Light,
            text = movieItems.Name + " - " + movieItems.Year,
            fontSize = 20.sp,
            textAlign = TextAlign.Center,
            color = Color.White
        )
        Text(
            modifier = Modifier.padding(20.dp),
            fontFamily = utilFont,
            fontWeight = FontWeight.Light,
            text = movieItems.Description,
            fontSize = 25.sp,
            lineHeight = 35.sp,
            textAlign = TextAlign.Center,
            color = Color.White
        )

        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 20.dp),
            fontFamily = utilFont,
            fontWeight = FontWeight.Light,
            text = "Director : " + movieItems.Director,
            fontSize = 20.sp,
            lineHeight = 35.sp,
            textAlign = TextAlign.Center,
            color = Color.White
        )
        Text(
            modifier = Modifier.padding(20.dp),
            fontFamily = utilFont,
            fontWeight = FontWeight.Light,
            text = "Cast : " + movieItems.Cast,
            fontSize = 20.sp,
            lineHeight = 35.sp,
            textAlign = TextAlign.Center,
            color = Color.White
        )


    }
}


@OptIn(ExperimentalAnimationApi::class)
@Composable
fun DetailsImage(uri: String) {
    Column {
        var visibleAnimation by remember { mutableStateOf(false) }
        LaunchedEffect(key1 = true) {
            delay(100L)
            visibleAnimation = true
        }
        AnimatedVisibility(
            visible = visibleAnimation,
            enter = scaleIn() + expandVertically(expandFrom = Alignment.CenterVertically),
            exit = scaleOut() + shrinkVertically(shrinkTowards = Alignment.CenterVertically)
        ) {
            val painter =
                rememberImagePainter(data = uri.replace(" ", "/"), builder = {
                    size(OriginalSize)
                    placeholder(R.drawable.ic_placeholder)
                    error(R.drawable.ic_placeholder)
                    crossfade(1000)

                })

            Image(
                painter = painter,
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxSize()
                    .blur(7.dp)
            )
        }
    }
}




