package com.amirhusseinsoori.grpckotlin.domain.exception

class GrpcResult<T> @PublishedApi internal constructor(
    @PublishedApi
    internal val value: Any?
) {
    fun exceptionOrNull(): Throwable? =
        when (value) {
            is Failure -> value.exception
            else -> null
        }

    override fun toString(): String =
        when (value) {
            is Failure -> value.toString() // "Failure($exception)"
            else -> "Success($value)"
        }

    companion object {
        /**
         * Returns an instance that encapsulates the given [value] as successful value.
         */

        fun <T> success(value: T): GrpcResult<T> =
            GrpcResult(value)

        /**
         * Returns an instance that encapsulates the given [Throwable] [exception] as failure.
         */

        fun <T> failure(exception: Throwable): GrpcResult<T> =
            GrpcResult(Failure(exception))
    }

    internal class Failure(
        val exception: Throwable
    ) {
        override fun equals(other: Any?): Boolean = other is Failure && exception == other.exception
        override fun hashCode(): Int = exception.hashCode()
        override fun toString(): String = "Failure($exception)"
    }


}









