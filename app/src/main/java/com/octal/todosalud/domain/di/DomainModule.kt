package com.octal.todosalud.domain.di

import com.octal.todosalud.data.datastore.AppDataStore
import com.octal.todosalud.data.network.TodoApiServices
import com.octal.todosalud.domain.appEvents.AppEventsHandler
import com.octal.todosalud.domain.appEvents.AppEventsHandlerImpl
import com.octal.todosalud.domain.usecases.*
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
    fun provideUserLoggedInUseCase(appDataStore: AppDataStore, todoApiServices: TodoApiServices): UserLoggedInUseCase =
        UserLoggedInUseCaseImpl(appDataStore, todoApiServices)

    @Singleton
    @Provides
    fun provideUserLogoutUseCase(appDataStore: AppDataStore): UserLogoutUseCase =
        UserLogoutUseCaseImpl(appDataStore)
    @Singleton
    @Provides
    fun provideForgotPasswordUseCase(todoApiServices: TodoApiServices):ForgotPasswordUseCase =
        ForgotPasswordUseCaseImpl(todoApiServices)

    @Singleton
    @Provides
    fun provideResetPasswordUseCase(todoApiServices: TodoApiServices):ResetPasswordUseCase =
        ResetPasswordUseCaseImpl(todoApiServices)

}
