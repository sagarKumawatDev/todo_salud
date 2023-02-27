package com.octal.todosalud.domain.appEvents

import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow

class AppEventsHandlerImpl : AppEventsHandler {
    private val _appEventsFlow = MutableSharedFlow<AppEvents>()

    override val appEventsFlow: SharedFlow<AppEvents>
        get() = _appEventsFlow.asSharedFlow()

    override suspend fun logout() {
        _appEventsFlow.emit(AppEvents.Logout)
    }

    override suspend fun login() {
        _appEventsFlow.emit(AppEvents.Login)
    }
}
