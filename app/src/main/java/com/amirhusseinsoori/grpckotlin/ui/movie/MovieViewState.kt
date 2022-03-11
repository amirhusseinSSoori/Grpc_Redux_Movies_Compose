package com.amirhusseinsoori.grpckotlin.ui.movie

import com.amirhusseinsoori.domain.entity.DomainMoviesItem
import com.amirhusseinsoori.grpckotlin.ui.redux.State


/**
 * An implementation of [State] that describes the configuration of the login screen at a given time.
 */
data class MovieViewState(
    val listMovies: List<DomainMoviesItem> = emptyList(),
    val showProgressBar: Boolean = false,
    val messageError: String? = null,
) : State