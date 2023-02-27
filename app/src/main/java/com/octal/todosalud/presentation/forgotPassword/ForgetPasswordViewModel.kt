package com.octal.todosalud.presentation.forgotPassword

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.octal.todosalud.domain.entities.ForgotPasswordRequest
import com.octal.todosalud.domain.entities.ForgotPasswordResponse
import com.octal.todosalud.domain.entities.LoginRequest
import com.octal.todosalud.domain.entities.LoginResponse
import com.octal.todosalud.domain.usecases.ForgotPasswordUseCase
import com.octal.todosalud.domain.usecases.UserLoggedInUseCase
import com.octal.todosalud.utility.ApiCallingState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ForgetPasswordViewModel @Inject constructor(
    private val forgotPasswordUseCase: ForgotPasswordUseCase
) : ViewModel() {

    private val _forgotPasswordApiFlow =
        MutableStateFlow<ApiCallingState<ForgotPasswordResponse>>(ApiCallingState.Idle)
    val forgotPasswordApiFlow: SharedFlow<ApiCallingState<ForgotPasswordResponse>> get() = _forgotPasswordApiFlow.asSharedFlow()

    fun callForgotPasswordApi(forgotPasswordRequest: ForgotPasswordRequest) {
        viewModelScope.launch {
            _forgotPasswordApiFlow.emit(ApiCallingState.Loading)
            forgotPasswordUseCase.execute(
                forgotPasswordRequest
            ).onSuccess {
                _forgotPasswordApiFlow.emit(ApiCallingState.Success(it))
            }.onFailure {
                _forgotPasswordApiFlow.emit(ApiCallingState.Failure.Unknown(throwable = it))
            }
        }
    }

    fun updateToIdleState() = viewModelScope.launch {
        _forgotPasswordApiFlow.emit(ApiCallingState.Idle)
    }

}