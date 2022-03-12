package com.amirhusseinsoori.grpckotlin.ui.movie.pattern

import com.amirhusseinsoori.grpckotlin.ui.redux.Effect

data class MovieEffect  (
    val messageError: String = "NoError"
): Effect