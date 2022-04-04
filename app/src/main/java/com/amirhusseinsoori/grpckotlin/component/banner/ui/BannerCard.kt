package com.amirhusseinsoori.grpckotlin.component.banner.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import com.amirhusseinsoori.grpckotlin.component.banner.model.BaseBannerBean
import com.amirhusseinsoori.grpckotlin.component.banner.utils.ImageLoader

/**
 * Banner
 *
 * @param bean banner Model
 * @param modifier
 * @param shape
 * @param contentScale
 * @param onBannerClick Banner
 */
@Composable
fun <T : BaseBannerBean> BannerCard(
    bean: T,
    modifier: Modifier = Modifier,
    shape: Shape = RoundedCornerShape(10.dp),
    contentScale: ContentScale,
    onBannerClick: () -> Unit,
) {
    if (bean.data == null) {
        throw NullPointerException("Url or imgRes or filePath must have a not for empty.")
    }

    Card(
        shape = shape,
        modifier = modifier
    ) {
        val imgModifier = Modifier.clickable(onClick = onBannerClick)
        ImageLoader(bean.data, imgModifier, contentScale)
    }
}
