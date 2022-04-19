package com.amirhusseinsoori.grpckotlin.component.banner.utils

import androidx.compose.ui.graphics.Color
import com.amirhusseinsoori.grpckotlin.ui.theme.bCard


sealed class ProvideGradiant( var list: List<Color>) {
    object PurpleAndWhite : ProvideGradiant(
        listOf(
            bCard,
            Color.White
        )
    )
    object WhiteAndWhite : ProvideGradiant(
        listOf(
            Color.White,
            Color.White
        )
    )
    object WhiteAndPurple : ProvideGradiant(
        listOf(
            Color.White,
            bCard
        )
    )
}