package com.amirhusseinsoori.grpckotlin.ui.movie.pattern

import com.amirhusseinsoori.domain.entity.DomainMoviesItem
import com.amirhusseinsoori.domain.entity.model.BannerModel
import com.amirhusseinsoori.domain.redux.Action


/**
 * These are all of the possible actions that can be triggered from the Movie screen.
 */
sealed class MovieAction : Action {
    data class ShowFamousMovie(val movies: List<DomainMoviesItem>) : MovieAction()
    data class ShowComedyMovie(val movies: List<DomainMoviesItem>) : MovieAction()
    data class ShowSerials(val movies: List<DomainMoviesItem>) : MovieAction()
    data class ShowSlider(val sliders: List<BannerModel>) : MovieAction()
    data class ShowSearch(val search: List<DomainMoviesItem>) : MovieAction()

    data class ShowDialog(val message:String) : MovieAction()
    object HideDialog : MovieAction()

    object ShowLoading : MovieAction()
    object HideLoading : MovieAction()
}
