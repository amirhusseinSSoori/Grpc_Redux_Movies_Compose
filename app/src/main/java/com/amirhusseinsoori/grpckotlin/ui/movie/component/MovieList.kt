package com.amirhusseinsoori.grpckotlin.ui.movie.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme.typography
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberImagePainter
import coil.size.OriginalSize
import coil.size.PixelSize
import coil.size.Scale
import coil.transform.RoundedCornersTransformation
import com.amirhusseinsoori.domain.entity.DomainMoviesItem
import com.amirhusseinsoori.grpckotlin.R
import com.amirhusseinsoori.grpckotlin.component.banner.utils.utilFont
import com.amirhusseinsoori.grpckotlin.ui.theme.ColorPrimary


@Composable
fun MovieList(type: String, movieItems: List<DomainMoviesItem>, list: List<Color>) {
    Spacer(modifier = Modifier.height(10.dp))
    Card(
        shape = RoundedCornerShape(corner = CornerSize(10.dp)),
        modifier = Modifier
            .fillMaxWidth()
            .height(300.dp)
            .padding(8.dp)
            .background(
                shape = RoundedCornerShape(corner = CornerSize(10.dp)),
                brush = Brush.verticalGradient(
                    colors = list
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
            text = type,
            style = typography.h6,
            textAlign = TextAlign.Left,
            fontFamily = utilFont,
            fontWeight = FontWeight.Light,
            color = Color.Black,
            fontSize = 18.sp
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(5.dp)
        ) {
            LazyRow {
                items(movieItems) {
                    MovieItems(it)
                }
            }
        }
    }
}


@Composable
fun MovieItems(movie: DomainMoviesItem) {
    Box(
        modifier = Modifier
            .padding(top = 30.dp, start = 10.dp, bottom = 10.dp)
            .background(
                shape = RoundedCornerShape(corner = CornerSize(10.dp)),
                brush = Brush.verticalGradient(
                    colors = listOf(
                        ColorPrimary,
                        Color.White
                    )
                )
            )
    ) {
        Column(
            modifier = Modifier
                .height(215.dp)
                .width(150.dp)
        ) {
            MovieImage(movie)
            Text(
                modifier = Modifier
                    .padding(start = 8.dp),
                textAlign = TextAlign.Center,
                text = movie.Name,
                fontFamily = utilFont,
                fontWeight = FontWeight.Light,
                fontSize = 12.sp, style = typography.h6
            )
            Text(
                modifier = Modifier
                    .padding(start = 8.dp),
                textAlign = TextAlign.Center,
                text = movie.Director,
                fontFamily = utilFont,
                fontWeight = FontWeight.Light,
                fontSize = 8.sp,
                style = typography.caption
            )
        }
    }
}

@Composable
private fun MovieImage(movie: DomainMoviesItem) {
    val painter =
        rememberImagePainter(data = movie.Picture, builder = {
            size(OriginalSize)
            placeholder(R.drawable.ic_placeholder)
            error(R.drawable.ic_placeholder)
            crossfade(1000)
//            transformations(CircleCropTransformation(),BlurTransformation(LocalContext.current))
           transformations(RoundedCornersTransformation(50f))
        })

    Image(
        painter = painter,
        contentDescription = null,
        contentScale = ContentScale.Crop,
        modifier = Modifier.fillMaxWidth()
            .padding(
                start = 10.dp,
                top = 10.dp,
                end = 10.dp,
                bottom = 10.dp
            )
            .size(150.dp)
            .clip(RoundedCornerShape(corner = CornerSize(40.dp)))
    )
}

@Preview
@Composable
fun PreviewMvItem() {
//    val mv = DataProvider.newmovie
//    MovieListItem(movie = mv, navigateToProfile = {})
}
