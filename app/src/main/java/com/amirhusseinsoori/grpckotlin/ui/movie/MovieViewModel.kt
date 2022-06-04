package com.amirhusseinsoori.grpckotlin.ui.movie


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.amirhusseinsoori.common.MovieTypes
import com.amirhusseinsoori.domain.entity.DomainMoviesItem
import com.amirhusseinsoori.domain.redux.LoggingMiddleware
import com.amirhusseinsoori.domain.redux.Store
import com.amirhusseinsoori.domain.usecase.ShowAllMovieUseCase
import com.amirhusseinsoori.domain.usecase.ShowListSliderUseCase
import com.amirhusseinsoori.grpckotlin.ui.movie.pattern.MovieAction
import com.amirhusseinsoori.grpckotlin.ui.movie.pattern.MovieEffect
import com.amirhusseinsoori.grpckotlin.ui.movie.pattern.MovieReducer
import com.amirhusseinsoori.grpckotlin.ui.movie.pattern.MovieViewState
import dagger.hilt.android.lifecycle.HiltViewModel
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
        viewModelScope.launch {
            subscribeEvents()
            callEvent()
        }
    }


    private fun subscribeEvents() {
        viewModelScope.launch {
            store.event.collect { event ->
                handleEvent(event)
            }
        }
    }


    fun callEvent() {
        store.setEvent(MovieAction.Dispatcher)
    }

    private suspend fun handleEvent(action: MovieAction) {
        when (action) {
            MovieAction.Dispatcher -> {
                showAllMovies()
                showSlider()
            }
            is MovieAction.ShowDialog -> {
                store.effect(MovieAction.ShowDialog(message = action.message ?: ""))
            }

            is MovieAction.HideLoading -> {
                store.effect(MovieAction.HideLoading)

            }
            is MovieAction.ShowLoading -> {
                store.effect(MovieAction.ShowLoading)
            }
        }
    }


    private fun showAllMovies() {
        viewModelScope.launch {
            combine(
                showAllMovieUseCase.execute(MovieTypes.ComedyType.type),
                showAllMovieUseCase.execute(MovieTypes.SerialsType.type),
                showAllMovieUseCase.execute(MovieTypes.PopularType.type),
            ) { comedy, serials, famous ->
                MainSate(comedy, serials, famous)
            }.onStart {
                store.effect(MovieAction.ShowLoading)
            }.catch {
                store.setEvent(MovieAction.HideLoading)
                store.dispatch(MovieAction.ShowDialog(message = it.message ?: ""))
            }.onCompletion {
                store.setEvent(MovieAction.HideLoading)
            }.collect() {
                store.dispatch(MovieAction.HideDialog)
                store.dispatch(MovieAction.ShowComedyMovie(it.comedy))
                store.dispatch(MovieAction.ShowSerials(it.serials))
                store.dispatch(MovieAction.ShowFamousMovie(it.famous))
            }
        }
    }

    data class MainSate(
        val comedy: List<DomainMoviesItem>,
        val serials: List<DomainMoviesItem>,
        val famous: List<DomainMoviesItem>,
    )


    private fun showSlider() {
        viewModelScope.launch {
            showListSliderUseCase.execute().collect {
                store.dispatch(MovieAction.ShowSlider(it))
            }
        }
    }


}
