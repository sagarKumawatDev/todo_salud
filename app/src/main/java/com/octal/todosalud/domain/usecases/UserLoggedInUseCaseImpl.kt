package com.octal.todosalud.domain.usecases

import com.octal.todosalud.data.datastore.AppDataStore
import com.octal.todosalud.data.network.TodoApiServices
import com.octal.todosalud.data.network.response.getOrNull
import com.octal.todosalud.data.network.response.toResult
import com.octal.todosalud.domain.entities.LoginResponse


const val LOGIN_TYPE = "email"

class UserLoggedInUseCaseImpl(
    private val appDataStore: AppDataStore,
    private val testApiService: TodoApiServices
) : UserLoggedInUseCase {
    override suspend fun execute(input: UserLoggedInUseCase.UserLoggedInInput): Result<LoginResponse> {
        val result = testApiService.login(input.request)
        result.getOrNull()?.let {
            saveUserData(it)
        }
        return result.toResult()
    }

    private suspend fun saveUserData(userData: LoginResponse) {
        if (userData.success && userData.data?.isEmailVerified == true) {
            appDataStore.updateData {
                it.copy(
                    isUserLoggedIn = true,
                    userName = userData.data?.name.orEmpty(),
                    userToken = userData.data?.accessToken.orEmpty(),
                    isEmailVerified = userData.data?.isEmailVerified ?: false,
                    loginType = LOGIN_TYPE,
                    )
            }
        }
    }
}
