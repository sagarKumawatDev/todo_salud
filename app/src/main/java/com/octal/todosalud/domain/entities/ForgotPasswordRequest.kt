package com.octal.todosalud.domain.entities

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class ForgotPasswordRequest(
    @SerializedName("email")
    @Expose
    val email: String? = ""
)

