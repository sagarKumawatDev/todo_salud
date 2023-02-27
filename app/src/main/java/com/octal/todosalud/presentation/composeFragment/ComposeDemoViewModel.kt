package com.octal.todosalud.presentation.composeFragment

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.octal.todosalud.presentation.composeFragment.stateAndEvents.ComposeScreenEvents
import com.octal.todosalud.presentation.composeFragment.stateAndEvents.ComposeScreenState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@HiltViewModel
class ComposeDemoViewModel @Inject constructor() : ViewModel() {

    private val _composeScreenState = MutableStateFlow(ComposeScreenState())
    val composeScreenState = _composeScreenState.asStateFlow()

    private val _composeScreenEvents = MutableSharedFlow<ComposeScreenEvents>()
    val composeScreenEvents = _composeScreenEvents.asSharedFlow()

    suspend fun emitComposeScreenEvents(composeScreenEvents: ComposeScreenEvents) =
        _composeScreenEvents.emit(composeScreenEvents)

    init {
        observeComposeScreenEvents()
    }

    private fun observeComposeScreenEvents() {
        composeScreenEvents.onEach {
            when (it) {
                is ComposeScreenEvents.OnTextChanged -> _composeScreenState.emit(
                    composeScreenState.value.copy(
                        textInput = it.string
                    )
                )
                else -> Unit
            }
        }.launchIn(viewModelScope)
    }
}