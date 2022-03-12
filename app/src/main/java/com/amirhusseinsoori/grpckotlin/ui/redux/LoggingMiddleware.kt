package com.amirhusseinsoori.grpckotlin.ui.redux

import android.util.Log


/**
 * This [Middleware] is responsible for logging every [Action] that is processed to the Logcat, so
 * that we can use this for debugging.
 */
class LoggingMiddleware<S : State, E : Effect, A : Action> : Middleware<S, E, A> {
    override suspend fun process(action: A, currentState: S, effect: E, store: Store<S, E, A>) {
        Log.e(
            "LoggingMiddleware",
            "Processing action: $action; Current state: $currentState"
        )
    }
}