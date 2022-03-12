package com.amirhusseinsoori.domain.redux

interface Reducer<S: State,E:Effect, A: Action> {

    /**
     * Given a [currentState] and some [action] that the user took, produce a new [State].
     *
     * This will give us clear and predictable state management, that ensures each state is associated
     * with some specific user intent or action.
     */
    fun reduce(currentState: S, action: A): S
    fun reducer(currentEffect: E, action: A): E

}
