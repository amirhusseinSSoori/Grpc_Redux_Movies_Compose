package com.amirhusseinsoori.grpckotlin.ui.movie.pattern

import com.amirhusseinsoori.domain.entity.DomainMoviesItem
import com.amirhusseinsoori.domain.entity.model.BannerModel
import com.amirhusseinsoori.domain.redux.Action


/**
 * These are all of the possible actions that can be triggered from the Movie screen.
 */
sealed class MovieAction : Action {
    data class ShowAllMovie(val movies: List<DomainMoviesItem>) : MovieAction()
    data class ShowSlider(val sliders: List<BannerModel>) : MovieAction()
    object LoadingStarted : MovieAction()
    object LoadingFinished : MovieAction()
    data class ShowFailed(val errorMessage: String) : MovieAction()
    data class ShowHide(val errorMessage: String) : MovieAction()
    object DispatchMovies : MovieAction()
    object DispatchSlider : MovieAction()
}
