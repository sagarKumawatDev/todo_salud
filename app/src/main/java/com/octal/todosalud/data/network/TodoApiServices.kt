package com.octal.todosalud.data.network

import com.octal.todosalud.data.network.response.NetworkResponse
import com.octal.todosalud.domain.entities.*
import retrofit2.http.Body
import retrofit2.http.POST

interface TodoApiServices {

    @POST(ServiceNames.LOGIN)
    suspend fun login(@Body loginRequest: LoginRequest): NetworkResponse<LoginResponse>

    @POST(ServiceNames.FORGOT_PASSWORD)
    suspend fun forgotPassword(@Body forgotPasswordRequest: ForgotPasswordRequest)
    : NetworkResponse<ForgotPasswordResponse>

    @POST(ServiceNames.RESET_PASSWORD)
    suspend fun resetPassword(@Body resetPasswordRequest: ResetPasswordRequest)
    : NetworkResponse<ResetPasswordResponse>
}
