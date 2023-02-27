package com.octal.todosalud.presentation.forgotPassword

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.octal.todosalud.domain.entities.ForgotPasswordRequest
import com.octal.todosalud.domain.entities.ForgotPasswordResponse
import com.octal.todosalud.domain.entities.ResetPasswordRequest
import com.octal.todosalud.domain.entities.ResetPasswordResponse
import com.octal.todosalud.domain.usecases.ResetPasswordUseCase
import com.octal.todosalud.utility.ApiCallingState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ResetPasswordViewModel @Inject constructor(
    private val resetPasswordUseCase: ResetPasswordUseCase
) : ViewModel() {

    private val _resetPasswordApiFlow =
        MutableStateFlow<ApiCallingState<ResetPasswordResponse>>(ApiCallingState.Idle)
    val resetPasswordApiFlow: SharedFlow<ApiCallingState<ResetPasswordResponse>> get() = _resetPasswordApiFlow.asSharedFlow()

    fun callResetPasswordApi(resetPasswordRequest: ResetPasswordRequest) {
        viewModelScope.launch {
            _resetPasswordApiFlow.emit(ApiCallingState.Loading)
            resetPasswordUseCase.execute(
                resetPasswordRequest
            ).onSuccess {
                _resetPasswordApiFlow.emit(ApiCallingState.Success(it))
            }.onFailure {
                _resetPasswordApiFlow.emit(ApiCallingState.Failure.Unknown(throwable = it))
            }
        }
    }

    fun updateToIdleState() = viewModelScope.launch {
        _resetPasswordApiFlow.emit(ApiCallingState.Idle)
    }
}