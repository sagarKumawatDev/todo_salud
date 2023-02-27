package com.octal.todosalud.domain.entities

import com.google.gson.annotations.SerializedName

data class ResetPasswordResponse(
    @SerializedName("success")
    val status: Boolean = false,
    @SerializedName("message")
    val message: String? = "",
    @SerializedName("data")
    val data: String? = ""
)