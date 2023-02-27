package com.octal.todosalud.domain.entities

import com.google.gson.annotations.SerializedName

data class ForgotPasswordResponse(
    @SerializedName("success")
    val status: Boolean = false,
    @SerializedName("message")
    val message: String? = null,
    @SerializedName("data")
    val data: String? = null
)