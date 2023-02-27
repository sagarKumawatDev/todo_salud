package com.octal.todosalud.utility

import com.octal.todosalud.data.network.response.NetworkIOException

sealed class ApiCallingState<out T> {
    object Idle : ApiCallingState<Nothing>()
    object Loading : ApiCallingState<Nothing>()
    data class Success<T>(val value: T) : ApiCallingState<T>()
    sealed class Failure : ApiCallingState<Nothing>() {
        abstract val isAtLeastOnePageLoaded: Boolean
        abstract val throwable: Throwable

        data class Network(
            override val isAtLeastOnePageLoaded: Boolean = false,
            override val throwable: NetworkIOException,
        ) : Failure()

        data class Unknown(
            override val isAtLeastOnePageLoaded: Boolean = false,
            override val throwable: Throwable,
        ) : Failure()
    }

    operator fun invoke() = (this as? Success)?.value
}
