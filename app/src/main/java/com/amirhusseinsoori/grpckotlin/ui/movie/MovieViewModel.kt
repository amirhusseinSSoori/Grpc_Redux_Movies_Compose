package com.amirhusseinsoori.grpckotlin.ui.movie

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.amirhusseinsoori.domain.exception.fold
import com.amirhusseinsoori.domain.usecase.ShowAllMovieUseCase
import com.amirhusseinsoori.grpckotlin.ui.redux.LoggingMiddleware
import com.amirhusseinsoori.grpckotlin.ui.redux.Store


import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class MovieViewModel @Inject constructor(private val showAllMovieUseCase: ShowAllMovieUseCase) :
    ViewModel() {
    
    private val store = Store(
        initialState = MovieViewState(),
        reducer = MovieReducer(),
        middlewares = listOf(
            LoggingMiddleware(),
        )
    )
    val viewState: StateFlow<MovieViewState> = store.state

    init {
        setData()
    }


    private fun setData() {
        viewModelScope.launch {
            showAllMovieUseCase.execute().collect() { result ->
                result.fold(onSuccess = {
                    store.dispatch(MoviesAction.LoadingFinished)
                    store.dispatch(MoviesAction.ShowAllMovie(it))
                }, onLoading = {
                    store.dispatch(MoviesAction.LoadingStarted)
                }, onFailure = {
                    store.dispatch(MoviesAction.LoadingFinished)
                    store.dispatch(MoviesAction.ShowFailed(it))
                })

            }
        }
    }


}