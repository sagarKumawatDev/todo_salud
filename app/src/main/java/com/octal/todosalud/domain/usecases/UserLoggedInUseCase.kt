package com.octal.todosalud.domain.usecases

import com.octal.todosalud.domain.entities.LoginRequest
import com.octal.todosalud.domain.entities.LoginResponse

interface UserLoggedInUseCase : SuspendingUseCase<UserLoggedInUseCase.UserLoggedInInput, Result<LoginResponse>> {
    data class UserLoggedInInput(val request: LoginRequest)
}
