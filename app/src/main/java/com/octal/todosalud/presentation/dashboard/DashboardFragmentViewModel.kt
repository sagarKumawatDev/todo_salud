package com.octal.todosalud.presentation.dashboard

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.octal.todosalud.domain.appEvents.AppEventsHandler
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DashboardFragmentViewModel @Inject constructor(private val appEventsHandler: AppEventsHandler) :
    ViewModel() {
    fun logout() = viewModelScope.launch {
        appEventsHandler.logout()
    }
}
