package com.amirhusseinsoori.grpckotlin.ui.movie.pattern

import com.amirhusseinsoori.domain.redux.Effect


data class MovieEffect(
    val showProgressBar: Boolean = false,
) : Effect



