package com.octal.todosalud.domain.entities

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * Created by KP on 1/4/2019.
 */
data class LoginResponse(
    @field:Expose
    @field:SerializedName("success")
    var success: Boolean,
    @field:Expose
    @field:SerializedName(
        "message"
    ) var message: String
) {

    @SerializedName("data")
    @Expose
    var data: Data? = null

    inner class Data {
        @SerializedName("id")
        @Expose
        var id: Int? = null

        @SerializedName("name")
        @Expose
        var name: String? = null

        @SerializedName("access_token")
        @Expose
        var accessToken: String? = null

        @SerializedName("email_verified")
        @Expose
        val isEmailVerified: Boolean? = null

        @SerializedName("msg")
        @Expose
        val message: String? = null
    }
}