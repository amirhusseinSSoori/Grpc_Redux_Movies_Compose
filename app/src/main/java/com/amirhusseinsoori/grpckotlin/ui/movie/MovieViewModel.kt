package com.amirhusseinsoori.grpckotlin.ui.movie

import android.util.Log
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
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.*

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
            is MovieAction.DispatchSlider -> {
                showSlider()
            }
        }
    }


    @OptIn(FlowPreview::class)
    private fun showAllMovies() {
        viewModelScope.launch {
            combine(
                showAllMovieUseCase.execute("ListVideos1"),
                showAllMovieUseCase.execute("ListVideos2"),
                showAllMovieUseCase.execute("ListVideos3")
            ) { a, b,c ->
                store.dispatch(MovieAction.ShowComedyMovie(a))
                store.dispatch(MovieAction.ShowFamousMovie(b))
            }.catch {
                store.effect(MovieAction.ShowFailed(it.message!!)) { MovieEffect(it.message!!) }
            }.onStart {
                store.dispatch(MovieAction.LoadingStarted)
            }.onCompletion {
                store.dispatch(MovieAction.LoadingFinished)
            }.collect()

        }
    }

    private fun showSlider() {
        viewModelScope.launch {
            showListSliderUseCase.execute().collect {
                store.dispatch(MovieAction.ShowSlider(it))
            }
        }
    }


}