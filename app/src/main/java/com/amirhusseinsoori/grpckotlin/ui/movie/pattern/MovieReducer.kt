package com.amirhusseinsoori.grpckotlin.ui.movie.pattern

import com.amirhusseinsoori.domain.redux.Reducer


/**
 * This reducer is responsible for handling any [MovieAction], and using that to create
 * a new [MovieViewState].
 */
class MovieReducer : Reducer<MovieViewState, MovieEffect, MovieAction> {

    /**
     * Side note: Notice that all of the functions are named in a way that they signify they're
     * returning a new state, and not just processing information. This helps keep your when statements
     * clear that they're returning stuff, so that context isn't lost.
     */
    override fun reduce(currentState: MovieViewState, action: MovieAction): MovieViewState {
        return when (action) {
            is MovieAction.ShowSlider ->{
                displaySlider(currentState,action)
            }
            is MovieAction.ShowAllMovie -> {
                displayMovieList(currentState,action)
            }
            is MovieAction.LoadingStarted -> {
                stateAfterLoadingStarted(currentState)
            }
            MovieAction.LoadingFinished -> {
                stateAfterLoadingCompleted(currentState)
            }
            else -> currentState
        }
    }
    override fun reducer(currentEffect: MovieEffect, action: MovieAction): MovieEffect {
        return when (action) {
            is MovieAction.ShowFailed -> {
                stateShowError(currentEffect)
            }
            is MovieAction.ShowHide -> {
                stateHideError(currentEffect)
            }
            else -> currentEffect
        }
    }


    private fun stateLoadSlider(currentState: MovieViewState) =
        currentState.copy(
            slider = currentState.slider,
        )



    private fun stateShowError(currentState: MovieEffect) =
        currentState.copy(
            messageError =currentState.messageError,
        )

    private fun stateHideError(currentState: MovieEffect) =
        currentState.copy(
            messageError ="NoError",
        )

    private fun stateAfterLoadingStarted(currentState: MovieViewState) =
        currentState.copy(
            showProgressBar = true,
        )

    private fun stateAfterLoadingCompleted(currentState: MovieViewState) =
        currentState.copy(
            showProgressBar = false,
        )



    private fun displayMovieList(
        currentState: MovieViewState,
        action: MovieAction.ShowAllMovie
    ) = currentState.copy(
        listMovies = action.movies,
    )

    private fun displaySlider(
        currentState: MovieViewState,
        action: MovieAction.ShowSlider
    ) = currentState.copy(
        slider = action.sliders,
    )



}