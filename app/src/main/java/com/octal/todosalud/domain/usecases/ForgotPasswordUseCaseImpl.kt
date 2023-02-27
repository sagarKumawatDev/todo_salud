package com.octal.todosalud.domain.usecases

import com.octal.todosalud.data.network.TodoApiServices
import com.octal.todosalud.data.network.response.toResult
import com.octal.todosalud.domain.entities.ForgotPasswordRequest
import com.octal.todosalud.domain.entities.ForgotPasswordResponse

class ForgotPasswordUseCaseImpl(
    private val todoApiServices: TodoApiServices
): ForgotPasswordUseCase{
    override suspend fun execute(input: ForgotPasswordRequest): Result<ForgotPasswordResponse> =
        todoApiServices.forgotPassword(input).toResult()
}