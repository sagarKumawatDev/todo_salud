package com.octal.todosalud.domain.usecases

import com.octal.todosalud.domain.entities.ResetPasswordRequest
import com.octal.todosalud.domain.entities.ResetPasswordResponse

interface ResetPasswordUseCase: SuspendingUseCase<ResetPasswordRequest, Result<ResetPasswordResponse>>