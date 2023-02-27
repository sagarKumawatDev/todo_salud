package com.octal.todosalud.domain.usecases

interface UseCase<in InputT, out OutputT> {
    fun execute(input: InputT): OutputT
}

interface SuspendingUseCase<in InputT, out OutputT> {
    suspend fun execute(input: InputT): OutputT
}

interface NoInputUseCase<out OutputT> {
    fun execute(): OutputT
}

interface NoInputSuspendingUseCase<out OutputT> {
    suspend fun execute(): OutputT
}
