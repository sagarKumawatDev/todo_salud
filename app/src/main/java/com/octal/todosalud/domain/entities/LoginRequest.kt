package com.octal.todosalud.domain.entities

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * Created by KP on 1/18/2019.
 */
data class LoginRequest(
    @SerializedName("email")
    @Expose
    val email: String? = null,
    @SerializedName("password")
    @Expose
    val password: String? = null,
    @SerializedName("player_id")
    @Expose
    val playerId: String? = null
)