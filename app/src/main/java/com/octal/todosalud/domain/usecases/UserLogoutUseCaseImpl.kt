package com.octal.todosalud.domain.usecases

import com.octal.todosalud.data.datastore.AppDataStore

class UserLogoutUseCaseImpl(val appDataStore: AppDataStore) : UserLogoutUseCase {
    override suspend fun execute(): Boolean {
        appDataStore.updateData {
            it.copy(isUserLoggedIn = false)
        }
       return true
    }
}
