package com.amirhusseinsoori.domain.redux


import com.google.common.truth.Truth.assertThat

/**
 * This is a implementation of [Middleware] that will log every action sent to it.
 *
 * In a unit test, we can apply this middleware to a Store, and then use this middleware for asserting
 * that an action was sent to that store.
 */
class ActionCaptureMiddleware<S: State,E:Effect, A: Action> : Middleware<S,E, A> {
    private val actionHistory: MutableList<Action> = mutableListOf()

    override suspend fun process(action: A, currentState: S, effect: E, store: Store<S, E, A>) {
        actionHistory.add(action)
    }

    fun assertActionProcessed(expectedAction: Action) {
        assertThat(actionHistory).contains(expectedAction)
    }
}