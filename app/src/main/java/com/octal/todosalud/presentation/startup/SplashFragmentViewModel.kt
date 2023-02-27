package com.octal.todosalud.presentation.startup

import androidx.lifecycle.ViewModel
import com.octal.todosalud.domain.usecases.IsUserLoggedInUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SplashFragmentViewModel @Inject constructor(private val isUserLoggedInUseCase: IsUserLoggedInUseCase) :
    ViewModel() {
    suspend fun isUserLoggedIn(): Boolean = isUserLoggedInUseCase.execute()
}
