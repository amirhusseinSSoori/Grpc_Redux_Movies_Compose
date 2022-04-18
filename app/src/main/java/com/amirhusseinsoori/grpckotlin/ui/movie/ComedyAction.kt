package com.example.movieapp.ui.component

import android.graphics.Movie
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme.typography
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.amirhusseinsoori.grpckotlin.R
import com.amirhusseinsoori.domain.entity.DomainMoviesItem
import com.amirhusseinsoori.grpckotlin.ui.theme.bCard


@Composable
fun MovieListItem(movie: DomainMoviesItem) {
    ConstraintLayout(
        modifier = Modifier
            .padding(top = 30.dp, start = 10.dp, bottom = 10.dp)
            .background(
                shape = RoundedCornerShape(corner = CornerSize(10.dp)),
                brush = Brush.verticalGradient(
                    colors = listOf(
                        bCard,
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
                fontSize = 12.sp, style = typography.h6
            )
            Text(
                modifier = Modifier
                    .padding(start = 5.dp),
                textAlign = TextAlign.Center,
                text = movie.Description,
                fontSize = 8.sp,
                style = typography.caption
            )
        }
    }
}

@Composable
private fun MovieImage(movie: DomainMoviesItem) {
    val painter =
        rememberImagePainter(data = movie.Picture) {
            crossfade(durationMillis = 1000)
            error(R.drawable.ic_baseline_error_24)
            placeholder(R.drawable.ic_placeholder)
        }
    Image(
        painter = painter,
        contentDescription = null,
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .padding(10.dp)
            .size(150.dp)
            .clip(RoundedCornerShape(corner = CornerSize(10.dp)))
    )
}

@Preview
@Composable
fun PreviewMvItem() {
//    val mv = DataProvider.newmovie
//    MovieListItem(movie = mv, navigateToProfile = {})
}
