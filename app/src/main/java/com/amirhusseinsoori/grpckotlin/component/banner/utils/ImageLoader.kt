package com.amirhusseinsoori.grpckotlin.component.banner.utils

import android.graphics.BitmapFactory
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.graphics.painter.BitmapPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import coil.compose.rememberAsyncImagePainter

private const val TAG = "ImageLoader"

/**
 *
 * @param data
 * @param modifier
 * @param contentScale
 */
@Composable
fun ImageLoader(
    data: Any?,
    modifier: Modifier = Modifier,
    contentScale: ContentScale = ContentScale.Fit,
) {
    when (data) {
        is String -> {
            val painter = if (data.contains("https://") || data.contains("http://")) {
                rememberAsyncImagePainter(
                    model = data,
                )
            } else {
                val bitmap = BitmapFactory.decodeFile(data)
                BitmapPainter(bitmap.asImageBitmap())
            }
            Image(
                modifier = modifier,
                painter = painter,
                contentDescription = "",
                contentScale = contentScale
            )
        }
        is Int -> {
            Image(
                modifier = modifier,
                painter = painterResource(data),
                contentDescription = "",
                contentScale = contentScale
            )
        }
        else -> {
            throw IllegalArgumentException("Error：url、Error drawable id")
        }
    }
}