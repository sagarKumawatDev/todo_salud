package com.octal.todosalud.data.datastore

import kotlinx.serialization.Serializable

@Serializable
data class AppDataStoreDto(
    val isUserLoggedIn: Boolean = false,
    val userToken: String = "",
    val loginType: String = "",
    val userName: String = "",
    val isGuest: Boolean = false,
    val isEmailVerified: Boolean = false,
)
