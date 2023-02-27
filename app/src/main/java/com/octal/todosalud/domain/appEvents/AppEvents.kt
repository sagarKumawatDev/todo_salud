package com.octal.todosalud.domain.appEvents

sealed interface AppEvents {
    object Logout : AppEvents
    object Login : AppEvents
}
