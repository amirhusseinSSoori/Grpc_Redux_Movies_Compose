package com.amirhusseinsoori.grpckotlin.component.banner.ui.config

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

data class BannerConfig(
    var bannerHeight: Dp = 250.dp,
    var bannerImagePadding: Dp = 8.dp,
    var shape: Shape = RoundedCornerShape(10.dp),
    var intervalTime: Long = 3000,
    var contentScale: ContentScale = ContentScale.FillBounds,
    var repeat: Boolean = true,
    var itemSpacing: Dp = 0.dp,
    var contentPadding: PaddingValues = PaddingValues(0.dp),
)