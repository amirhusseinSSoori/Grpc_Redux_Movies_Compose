package com.amirhusseinsoori.grpckotlin.ui.movie


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.amirhusseinsoori.common.MovieTypes
import com.amirhusseinsoori.common.showMessage
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
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class MovieViewModel @Inject constructor(
    private val showAllMovieUseCase: ShowAllMovieUseCase,
    private val showListSliderUseCase: ShowListSliderUseCase
) : ViewModel() {


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
        callEvent()
    }

    fun callEvent() {
        showAllMovies()
        showSlider("")
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
                store.dispatch(MovieAction.HideLoading)
                store.dispatch(MovieAction.ShowDialog(message = it.message!!.showMessage()))
            }.onCompletion {
                store.effect(MovieAction.HideLoading)
            }.flowOn(Dispatchers.IO)
                .collect() {
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


    private fun showSlider(type:String) {
        viewModelScope.launch {
            showListSliderUseCase.execute(type).collect {
                store.dispatch(MovieAction.ShowSlider(it))
            }
        }
    }


}
