package com.octal.todosalud.data.network.response

import okhttp3.ResponseBody
import com.octal.todosalud.data.network.response.NetworkResponse.Success
import com.octal.todosalud.data.network.response.NetworkResponse.Failure

typealias Headers = Map<String, List<String>>

sealed class NetworkResponse<out T> {
    data class Success<T>(
        val statusCode: Int,
        val headers: Headers,
        val value: T
    ) : NetworkResponse<T>()

    sealed class Failure : NetworkResponse<Nothing>() {
        data class Http(
            val statusCode: Int,
            val headers: Headers,
            val rawBody: ResponseBody?
        ) : Failure()

        data class IO(
            val exception: NetworkIOException,
        ) : Failure()

        data class Unknown(
            val exception: Throwable,
        ) : Failure()
    }
}

inline fun <T, R> NetworkResponse<T>.map(
    mapper: (T) -> R,
): NetworkResponse<R> = when (this) {
    is Success -> Success(statusCode, headers, mapper(value))
    is Failure -> this
}

fun <T> NetworkResponse<T>.getOrNull(): T? = when (this) {
    is Success -> value
    is Failure -> null
}

fun <T> NetworkResponse<T>.getOrThrow(): T = when (this) {
    is Success -> value
    is Failure.Http -> throw HttpException(statusCode, rawBody)
    is Failure.IO -> throw exception
    is Failure.Unknown -> throw exception
}

fun <T> NetworkResponse<T>.toResult(): Result<T> = when (this) {
    is Success -> Result.success(value)
    is Failure.Http -> Result.failure(HttpException(statusCode, rawBody))
    is Failure.IO -> Result.failure(exception)
    is Failure.Unknown -> Result.failure(exception)
}
