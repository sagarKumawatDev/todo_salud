package com.octal.todosalud.data.network.connectionManager

import kotlinx.coroutines.flow.Flow

interface ConnectivityObserver {

    fun observe(): Flow<Status>

    val isNetworkAvailable: Boolean

    enum class Status {
        Available, Unavailable, Losing, Lost
    }
}