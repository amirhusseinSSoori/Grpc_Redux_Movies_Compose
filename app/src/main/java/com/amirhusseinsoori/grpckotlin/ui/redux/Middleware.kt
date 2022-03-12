package com.amirhusseinsoori.grpckotlin.ui.redux

/**
 * A [Middleware] is any class that deals with side effects of actions. This can be logging,
 * triggering network calls, and other examples.
 */
interface Middleware<S: State,E:Effect, A: Action> {
    /**
     * This will process the given [action] and [currentState] and determine if we need to
     * perform any side effects, or trigger a new action.
     *
     * @param[store] This is a reference to the [Store] that dispatched this action. We should only
     * call this with a _new_ action, and not trigger the same action again or risk ending up in a
     * loop.
     */
    suspend fun process(
        action: A,
        currentState: S,
        effect:E,
        store: Store<S,E, A>,
    )
}
