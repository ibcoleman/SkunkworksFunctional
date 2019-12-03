package com.memetoclasm.skunkworksfunctional.algebras.data.network.error

sealed class NetworkError : Throwable() {
    object Unauthorized : NetworkError()
    object NotFound : NetworkError()
    object ServerError : NetworkError()
}