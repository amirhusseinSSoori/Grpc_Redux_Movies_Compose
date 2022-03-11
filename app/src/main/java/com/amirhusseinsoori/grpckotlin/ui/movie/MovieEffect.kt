package com.amirhusseinsoori.grpckotlin.ui.movie

import com.amirhusseinsoori.domain.entity.DomainMoviesItem
import com.amirhusseinsoori.grpckotlin.ui.redux.Effect
import com.amirhusseinsoori.grpckotlin.ui.redux.State

data class MovieEffect(
    val messageError: String? = null,
) : Effect