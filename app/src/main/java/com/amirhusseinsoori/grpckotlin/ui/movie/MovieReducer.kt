package com.amirhusseinsoori.grpckotlin.ui.movie


import com.amirhusseinsoori.grpckotlin.ui.redux.Reducer

/**
 * This reducer is responsible for handling any [LoginAction], and using that to create
 * a new [LoginViewState].
 */
class MovieReducer : Reducer<MovieViewState, MoviesAction> {

    /**
     * Side note: Notice that all of the functions are named in a way that they signify they're
     * returning a new state, and not just processing information. This helps keep your when statements
     * clear that they're returning stuff, so that context isn't lost.
     */
    override fun reduce(currentState: MovieViewState, action: MoviesAction): MovieViewState {
        return when (action) {
            is MoviesAction.ShowAllMovie -> {
                displayMovieList(currentState,action)
            }
            is MoviesAction.LoadingStarted -> {
                stateAfterLoadingStarted(currentState)
            }
            MoviesAction.LoadingFinished-> {
                stateAfterLoadingCompleted(currentState)
            }

            else -> currentState
        }
    }



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
        action: MoviesAction.ShowAllMovie
    ) = currentState.copy(
        listMovies = action.list,
    )


}