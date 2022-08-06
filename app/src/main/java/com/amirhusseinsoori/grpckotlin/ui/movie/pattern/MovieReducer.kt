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
            is MovieAction.ShowSlider -> {
                displaySlider(currentState, action)
            }
            is MovieAction.ShowFamousMovie -> {
                displayMovieFamousList(currentState, action)
            }
            is MovieAction.ShowComedyMovie -> {
                displayComedyList(currentState, action)
            }
            is MovieAction.ShowSerials -> {
                displaySerialsList(currentState, action)
            }
            is MovieAction.ShowSearch -> {
                displaySearchList(currentState, action)
            }
            is MovieAction.ShowDialog -> {
                showDialog(currentState, message = action.message)
            }
            is MovieAction.HideDialog -> {
                hideDialog(currentState)
            }
            else -> currentState
        }
    }

    override fun reducer(currentEffect: MovieEffect, action: MovieAction): MovieEffect {
        return when (action) {
//            is MovieAction.ShowErrorDialog -> {
//                stateShowError(currentEffect, message = action.message)
//            }
            MovieAction.ShowLoading -> {
                stateShowLoading(currentEffect)
            }
            MovieAction.HideLoading -> {
                stateHideLoading(currentEffect)
            }

            else -> currentEffect
        }
    }

    private fun showDialog(currentState: MovieViewState, message: String) =
        currentState.copy(
            DiaLog = true,
            message = message
        )

    private fun hideDialog(currentState: MovieViewState) =
        currentState.copy(
            DiaLog = false,
        )


    private fun stateShowLoading(currentState: MovieEffect) =
        currentState.copy(
            showProgressBar = true
        )


    private fun stateHideLoading(currentEffect: MovieEffect) =
        currentEffect.copy(
            showProgressBar = false
        )


    private fun displayComedyList(
        currentState: MovieViewState,
        action: MovieAction.ShowComedyMovie
    ) = currentState.copy(
        listComedy = action.movies,
    )

    private fun displaySearchList(
        currentState: MovieViewState,
        action: MovieAction.ShowSearch
    ) = currentState.copy(
        searchList = action.search,
    )


    private fun displaySerialsList(
        currentState: MovieViewState,
        action: MovieAction.ShowSerials
    ) = currentState.copy(
        listSerials = action.movies,
    )

    private fun displayMovieFamousList(
        currentState: MovieViewState,
        action: MovieAction.ShowFamousMovie
    ) = currentState.copy(
        listFamous = action.movies,
    )

    private fun displaySlider(
        currentState: MovieViewState,
        action: MovieAction.ShowSlider
    ) = currentState.copy(
        slider = action.sliders,
    )


}