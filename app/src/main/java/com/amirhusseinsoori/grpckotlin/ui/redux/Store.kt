package com.amirhusseinsoori.grpckotlin.ui.redux


import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

/**
 * A [Store] is our state container for a given screen.
 *
 * @param[initialState] This is the initial state of the screen when it is first created.
 * @param[reducer] A system for taking in the current state, and a new action, and outputting the
 * updated state.
 * @param[middlewares] This is a list of [Middleware] entities for handling any side effects
 * for actions dispatched to this store.
 */
class Store<S : State, E : Effect, A : Action>(
    initialState: S,
    val initialEffect: E,
    private val reducer: Reducer<S, E, A>,
    private val middlewares: List<Middleware<S, E, A>> = emptyList(),
) {

    private val _state = MutableStateFlow(initialState)
    val state: StateFlow<S> = _state

    private val currentState: S
        get() = _state.value


    private val _effect: Channel<E> = Channel()
    val effect = _effect.receiveAsFlow()

    private val _event : MutableSharedFlow<A> = MutableSharedFlow()
    val event = _event.asSharedFlow()


    fun setEvent(event : A) {
        val newEvent = event
        CoroutineScope(Dispatchers.IO).launch { _event.emit(newEvent) }
    }

    suspend fun dispatch(action: A) {
        middlewares.forEach { middleware ->
            middleware.process(action, currentState, initialEffect, this)
        }

        val newState = reducer.reduce(currentState, action)
        _state.value = newState

    }

    suspend fun effect(action: A, effect: () -> E) {
        val effectValue = reducer.reducer(effect(), action)
        _effect.send(effectValue)
    }

}
