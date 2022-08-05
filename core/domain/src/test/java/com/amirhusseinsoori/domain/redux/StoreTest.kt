package com.amirhusseinsoori.domain.redux


import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.*
import org.junit.Test



class StoreTest {
    @Test
    fun dispatchSendsActionToReducerAndMiddlewares() {
        // Given
        val inputState = TestState
        val inputEffect = TestEffect
        val inputAction = TestAction
        val reducer = TestReducer()
        val middleware = ActionCaptureMiddleware<State, Effect, Action>()

        val store = Store(
            inputState,
            inputEffect,
            reducer,
            listOf(middleware)
        )

        // When
        runBlocking {
            runBlocking {
                store.dispatch(inputAction)
            }

            // Then
            middleware.assertActionProcessed(inputAction)
            reducer.assertActionProcessed(inputAction)

        }
    }



}



object TestState : State
object TestAction : Action
object TestEffect : Effect


class TestReducer : Reducer<State, Effect, Action> {
    private val actionHistory: MutableList<Action> = mutableListOf()
    override fun reduce(currentState: State, action: Action): State {
        actionHistory.add(action)
        return currentState
    }
    override fun reducer(currentEffect: Effect, action: Action): Effect {
        actionHistory.add(action)
        return currentEffect
    }
    fun assertActionProcessed(expectedAction: Action) {
        assertThat(actionHistory).contains(expectedAction)
    }


}


