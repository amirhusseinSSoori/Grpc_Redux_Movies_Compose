package com.amirhusseinsoori.grpckotlin.ui.movie.pattern

import com.amirhusseinsoori.domain.entity.DomainMoviesItem
import com.amirhusseinsoori.domain.entity.model.BannerModel
import com.amirhusseinsoori.domain.redux.State


/**
 * An implementation of [State] that describes the configuration of the login screen at a given time.
 */
data class MovieViewState(
    val listFamous: List<DomainMoviesItem> = emptyList(),
    val listComedy: List<DomainMoviesItem> = emptyList(),
    val slider:List<BannerModel>? = emptyList(),
    val showProgressBar: Boolean = false,
) : State