package com.octal.todosalud.data.network

import com.octal.todosalud.data.network.response.NetworkResponse
import com.octal.todosalud.domain.entities.LoginRequest
import com.octal.todosalud.domain.entities.LoginResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface TodoApiServices {

    @POST(ServiceNames.LOGIN)
    suspend fun login(@Body loginRequest: LoginRequest): NetworkResponse<LoginResponse>
}
