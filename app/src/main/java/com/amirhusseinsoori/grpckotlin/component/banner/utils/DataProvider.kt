package com.amirhusseinsoori.grpckotlin.component.banner.utils

import androidx.compose.ui.graphics.Color
import com.amirhusseinsoori.grpckotlin.ui.theme.ColorPrimary
import com.amirhusseinsoori.grpckotlin.ui.theme.LightColor



sealed class ProvideGradiant( var list: List<Color>) {
    object BlackAndWhite : ProvideGradiant(
        listOf(
            ColorPrimary,
            Color.White
        )
    )
    object WhiteAndLightBack : ProvideGradiant(
        listOf(
            ColorPrimary,
            LightColor,
            Color.White,
        )
    )
    object WhiteAndBlack : ProvideGradiant(
        listOf(
            Color.White,
            ColorPrimary
        )
    )
}