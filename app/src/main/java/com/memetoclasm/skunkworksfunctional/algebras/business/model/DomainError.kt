package com.memetoclasm.skunkworksfunctional.algebras.business.model

import arrow.core.None
import arrow.core.Option
import arrow.core.Some
import com.memetoclasm.skunkworksfunctional.algebras.data.network.error.NetworkError

sealed class DomainError {

    companion object {
        fun fromThrowable(e: Throwable): DomainError =
            when (e) {
                is NetworkError.Unauthorized -> AuthenticationError
                is NetworkError.NotFound -> NotFoundError
                is NetworkError.ServerError -> ServerError
                else -> UnknownServerError((Some(e)))
            }
    }

    object AuthenticationError : DomainError()
    object NotFoundError : DomainError()
    object ServerError : DomainError()
    data class UnknownServerError(val e: Option<Throwable> = None) : DomainError()
}
