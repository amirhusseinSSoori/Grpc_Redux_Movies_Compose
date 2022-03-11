package com.amirhusseinsoori.grpckotlin.ui.movie

import com.amirhusseinsoori.domain.entity.DomainMoviesItem
import com.amirhusseinsoori.grpckotlin.ui.redux.Action

/**
 * These are all of the possible actions that can be triggered from the Movie screen.
 */
sealed class MoviesAction : Action {
    data class ShowAllMovie(val list: List<DomainMoviesItem>) : MoviesAction()
    object LoadingStarted : MoviesAction()
    object LoadingFinished : MoviesAction()
    data class ShowFailed(val error: Throwable?) : MoviesAction()
}
