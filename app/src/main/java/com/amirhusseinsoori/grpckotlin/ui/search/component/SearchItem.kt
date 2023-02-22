package com.amirhusseinsoori.grpckotlin.ui.search.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstrainScope
import androidx.constraintlayout.compose.ConstrainedLayoutReference
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintLayoutScope
import coil.compose.rememberImagePainter
import coil.size.OriginalSize
import coil.transform.RoundedCornersTransformation
import com.amirhusseinsoori.domain.entity.DomainMoviesItem
import com.amirhusseinsoori.grpckotlin.R
import com.amirhusseinsoori.grpckotlin.component.banner.utils.ProvideGradiant
import com.amirhusseinsoori.grpckotlin.component.banner.utils.utilFont



@Composable
fun SearchItem(movie: DomainMoviesItem) {
    ConstraintLayout(
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(150.dp)
            .padding(8.dp)
            .background(
                shape = RoundedCornerShape(corner = CornerSize(10.dp)),
                brush = Brush.verticalGradient(
                    colors = ProvideGradiant.WhiteAndLightBack.list

                )
            )
    ) {
        val (image, title, des, views) = createRefs()

        SearchImage(movie, image)

        Text(
            modifier = Modifier.constrainAs(title) {
                top.linkTo(parent.top, 24.dp)
                start.linkTo(image.end, 16.dp)
            },
            fontFamily = utilFont,
            fontWeight = FontWeight.Light,
            text = movie.Name,
            color = Color.White
        )

        Text(
            modifier = Modifier.constrainAs(des) {
                top.linkTo(title.bottom, 16.dp)
                start.linkTo(image.end, 16.dp)
            },
            fontFamily = utilFont,
            fontWeight = FontWeight.Light,
            text = movie.Director,
            color = Color.White
        )
        Text(
            modifier = Modifier.constrainAs(views) {
                top.linkTo(des.bottom, 16.dp)
                start.linkTo(image.end, 16.dp)
            },
            fontFamily = utilFont,
            fontWeight = FontWeight.Light,
            text = "views : ${movie.Views.toString()}",
            color = Color.White
        )


    }
}

@Composable
private fun ConstraintLayoutScope.SearchImage(
    movie: DomainMoviesItem,
    ref: ConstrainedLayoutReference
) {

    val painter =
        rememberImagePainter(data = movie.Picture, builder = {
            size(OriginalSize)
            placeholder(R.drawable.ic_placeholder)
            error(R.drawable.ic_placeholder)
            crossfade(1000)
            transformations(RoundedCornersTransformation(50f))
        })

    Image(
        painter = painter,
        contentDescription = null,
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .constrainAs(ref) {
                top.linkTo(parent.top, margin = 4.dp)
                start.linkTo(parent.start, margin = 10.dp)
                bottom.linkTo(parent.bottom, margin = 4.dp)
            }
            .size(150.dp)
            .padding(
                start = 10.dp,
                top = 10.dp,
                end = 10.dp,
                bottom = 10.dp
            )
            .clip(RoundedCornerShape(corner = CornerSize(40.dp)))
    )
}