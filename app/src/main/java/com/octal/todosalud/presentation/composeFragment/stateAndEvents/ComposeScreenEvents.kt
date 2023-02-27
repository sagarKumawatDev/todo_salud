package com.octal.todosalud.presentation.composeFragment.stateAndEvents

sealed interface ComposeScreenEvents{
    data class OnTextChanged(val string: String): ComposeScreenEvents
    object ShowToast: ComposeScreenEvents
}