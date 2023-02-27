package com.octal.todosalud.domain.usecases

import com.octal.todosalud.data.network.TodoApiServices
import com.octal.todosalud.data.network.response.toResult
import com.octal.todosalud.domain.entities.ResetPasswordRequest
import com.octal.todosalud.domain.entities.ResetPasswordResponse

class ResetPasswordUseCaseImpl(
    private val todoApiServices: TodoApiServices
) : ResetPasswordUseCase{
    override suspend fun execute(input: ResetPasswordRequest): Result<ResetPasswordResponse> =
        todoApiServices.resetPassword(input).toResult()
}