package com.amirhusseinsoori.domain.exception

inline fun <R, T> GrpcResult<T>.fold(
    onSuccess: (value: T) -> R,
    onFailure: (exception: Throwable) -> R
): R {

    return when (val exception = exceptionOrNull()) {
        null -> {
            onSuccess(value as T)
        }
        else -> {
            onFailure(exception)
        }
    }
}