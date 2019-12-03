package com.memetoclasm.skunkworksfunctional.algebras.data.network.mapper

import com.memetoclasm.skunkworksfunctional.algebras.data.network.error.NetworkError

fun Int.toNetworkError() = when (this) {
    401 -> NetworkError.Unauthorized
    else -> NetworkError.ServerError
}

fun Throwable.normalizeError() = when (this) {
    is NetworkError -> this
    else -> NetworkError.ServerError
}
