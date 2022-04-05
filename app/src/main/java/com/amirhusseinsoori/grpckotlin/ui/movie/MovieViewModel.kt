package com.amirhusseinsoori.grpckotlin.ui.movie

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.amirhusseinsoori.common.Constance.NoError
import com.amirhusseinsoori.domain.exception.LoadingOccurs
import com.amirhusseinsoori.domain.exception.fold
import com.amirhusseinsoori.domain.redux.LoggingMiddleware
import com.amirhusseinsoori.domain.redux.Store
import com.amirhusseinsoori.domain.usecase.ShowAllMovieUseCase
import com.amirhusseinsoori.domain.usecase.ShowListSliderUseCase
import com.amirhusseinsoori.grpckotlin.ui.movie.pattern.MovieAction
import com.amirhusseinsoori.grpckotlin.ui.movie.pattern.MovieEffect
import com.amirhusseinsoori.grpckotlin.ui.movie.pattern.MovieReducer
import com.amirhusseinsoori.grpckotlin.ui.movie.pattern.MovieViewState


import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow

import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class MovieViewModel @Inject constructor(
    private val showAllMovieUseCase: ShowAllMovieUseCase,
    private val showListSliderUseCase: ShowListSliderUseCase
) :
    ViewModel() {

    private val store = Store(
        initialState = MovieViewState(),
        initialEffect = MovieEffect(),
        reducer = MovieReducer(),
        middlewares = listOf(
            LoggingMiddleware(),
        )
    )
    val viewState: StateFlow<MovieViewState> = store.state
    val viewEffect: Flow<MovieEffect> = store.effect

    init {
        subscribeEvents()
        handleEvent(MovieAction.DispatchSlider)
        callEvent()

    }


    private fun subscribeEvents() {
        viewModelScope.launch {
            store.event.collect { event ->
                handleEvent(event)
            }
        }
    }


    fun callEvent() {
        store.setEvent(MovieAction.DispatchMovies)
    }


    private fun handleEvent(action: MovieAction) {
        when (action) {
            is MovieAction.DispatchMovies -> {
                showAllMovies()
            }
            is MovieAction.DispatchSlider->{
                showSlider()
            }
        }
    }


    private fun showAllMovies() {
        viewModelScope.launch {
            showAllMovieUseCase.execute().collect() { result ->
                result.fold(onSuccess = {
                    store.dispatch(MovieAction.ShowAllMovie(it))

                }, onLoading = { loading ->
                    when (loading) {
                        LoadingOccurs.StartLoading -> {
                            store.effect(MovieAction.ShowHide(NoError)) { MovieEffect(NoError) }
                            store.dispatch(MovieAction.LoadingStarted)
                        }
                        LoadingOccurs.FinishLoading -> {
                            store.dispatch(MovieAction.LoadingFinished)
                        }
                    }
                }) {
                    store.effect(MovieAction.ShowFailed(it.message!!)) { MovieEffect(it.message!!) }
                }
            }
        }
    }

    private fun showSlider(){
        viewModelScope.launch {
            showListSliderUseCase.execute().collect{
                store.dispatch(MovieAction.ShowSlider(it))
            }
        }
    }


}