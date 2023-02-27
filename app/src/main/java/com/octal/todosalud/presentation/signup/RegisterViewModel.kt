package com.octal.todosalud.presentation.signup

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.octal.todosalud.domain.appEvents.AppEventsHandler
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(private val appEventsHandler: AppEventsHandler) : ViewModel() {

    fun login() = viewModelScope.launch {
        appEventsHandler.login()
    }
}
