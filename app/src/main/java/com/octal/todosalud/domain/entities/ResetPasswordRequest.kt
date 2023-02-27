package com.octal.todosalud.domain.entities

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class ResetPasswordRequest(
    @SerializedName("email")
    @Expose
    val email: String? = null,

    @SerializedName("new_password")
    @Expose
    val newPassword: String? = null,

    @SerializedName("password_confirmation")
    @Expose
    val confirmPassword: String? = null
)