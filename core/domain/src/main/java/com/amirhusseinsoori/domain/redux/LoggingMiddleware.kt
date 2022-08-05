package com.amirhusseinsoori.domain.redux


import java.util.logging.Level


/**
 * This [Middleware] is responsible for logging every [Action] that is processed to the Logcat, so
 * that we can use this for debugging.
 */
class LoggingMiddleware<S : State, E : Effect, A : Action> : Middleware<S, E, A> {
    override suspend fun process(action: A, currentState: S, effect: E, store: Store<S, E, A>) {
        java.util.logging.Logger.getLogger("LoggingMiddleware").log(Level.WARNING, "Processing action: $action; Current state: $currentState")
    }
}