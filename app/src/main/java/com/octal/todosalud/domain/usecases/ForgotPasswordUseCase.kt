package com.octal.todosalud.domain.usecases

import com.octal.todosalud.domain.entities.ForgotPasswordRequest
import com.octal.todosalud.domain.entities.ForgotPasswordResponse

interface ForgotPasswordUseCase: SuspendingUseCase<ForgotPasswordRequest, Result<ForgotPasswordResponse>>