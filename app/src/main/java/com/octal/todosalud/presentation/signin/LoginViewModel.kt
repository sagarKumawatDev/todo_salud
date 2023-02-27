package com.octal.todosalud.presentation.signin

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.octal.todosalud.domain.appEvents.AppEventsHandler
import com.octal.todosalud.domain.entities.LoginRequest
import com.octal.todosalud.domain.entities.LoginResponse
import com.octal.todosalud.domain.usecases.UserLoggedInUseCase
import com.octal.todosalud.utility.ApiCallingState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val appEventsHandler: AppEventsHandler,
    private val isUserLoggedInUseCase: UserLoggedInUseCase
) : ViewModel() {

    fun login() = viewModelScope.launch {
        appEventsHandler.login()
    }

    private val _loginFlow =
        MutableStateFlow<ApiCallingState<LoginResponse>>(ApiCallingState.Idle)
    val loginFlow: SharedFlow<ApiCallingState<LoginResponse>> get() = _loginFlow.asSharedFlow()

    fun callLoginApi(loginRequest: LoginRequest) {
        viewModelScope.launch {
            _loginFlow.emit(ApiCallingState.Loading)
            isUserLoggedInUseCase.execute(
                UserLoggedInUseCase.UserLoggedInInput(
                    loginRequest
                )
            ).onSuccess {
                _loginFlow.emit(ApiCallingState.Success(it))
            }.onFailure {
                _loginFlow.emit(ApiCallingState.Failure.Unknown(throwable = it))
            }
        }
    }

    fun updateToIdleState() = viewModelScope.launch {
        _loginFlow.emit(ApiCallingState.Idle)
    }
}
