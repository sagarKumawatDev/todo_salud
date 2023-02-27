package com.octal.todosalud.domain.di

import com.octal.todosalud.data.datastore.AppDataStore
import com.octal.todosalud.data.network.TodoApiServices
import com.octal.todosalud.domain.appEvents.AppEventsHandler
import com.octal.todosalud.domain.appEvents.AppEventsHandlerImpl
import com.octal.todosalud.domain.usecases.IsUserLoggedInUseCase
import com.octal.todosalud.domain.usecases.IsUserLoggedInUseCaseImpl
import com.octal.todosalud.domain.usecases.UserLoggedInUseCase
import com.octal.todosalud.domain.usecases.UserLoggedInUseCaseImpl
import com.octal.todosalud.domain.usecases.UserLogoutUseCase
import com.octal.todosalud.domain.usecases.UserLogoutUseCaseImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DomainModule {
    @Singleton
    @Provides
    fun provideAppEventsHandler(): AppEventsHandler = AppEventsHandlerImpl()

    @Singleton
    @Provides
    fun provideIsUserLoggedInUseCase(appDataStore: AppDataStore): IsUserLoggedInUseCase =
        IsUserLoggedInUseCaseImpl(appDataStore)

    @Singleton
    @Provides
    fun provideUserLoggedInUseCase(appDataStore: AppDataStore): UserLoggedInUseCase =
        UserLoggedInUseCaseImpl(appDataStore)

    @Singleton
    @Provides
    fun provideUserLogoutUseCase(appDataStore: AppDataStore): UserLogoutUseCase =
        UserLogoutUseCaseImpl(appDataStore)

    @Singleton
    @Provides
    fun provideGetTestCatFactUseCase(testApiService: TodoApiServices): TestGetTestCatFactUseCase =
        TestGetTestCatFactUseCaseImpl(testApiService)
}
