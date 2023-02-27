package com.octal.todosalud.domain.usecases

import com.octal.todosalud.data.datastore.AppDataStore
import com.octal.todosalud.data.datastore.getData

class IsUserLoggedInUseCaseImpl(private val appDataStore: AppDataStore) : IsUserLoggedInUseCase {
    override suspend fun execute(): Boolean = appDataStore.getData().isUserLoggedIn
}
