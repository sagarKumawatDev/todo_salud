package com.octal.todosalud.presentation.mainActivity

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.octal.todosalud.domain.entities.TestCatFact
import com.octal.todosalud.domain.usecases.UserLoggedInUseCase
import com.octal.todosalud.domain.usecases.UserLogoutUseCase
import com.octal.todosalud.utility.ApiCallingState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor (
    private val userLoggedInUseCase: UserLoggedInUseCase,
    private val userLogoutUseCase: UserLogoutUseCase
) : ViewModel() {

    fun logoutClearData() = viewModelScope.launch {
        userLogoutUseCase.execute()
    }
}
