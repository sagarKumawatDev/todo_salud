package com.octal.todosalud.domain.appEvents

import kotlinx.coroutines.flow.SharedFlow

interface AppEventsHandler {
    val appEventsFlow: SharedFlow<AppEvents>

    suspend fun logout()

    suspend fun login()
}
