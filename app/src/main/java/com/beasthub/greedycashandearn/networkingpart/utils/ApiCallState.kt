package com.example.networking.utils

sealed class ApiCallState <out T> {
    data class Success<R>(var data:R):ApiCallState<R>()
    data class Error<E>(var error:E):ApiCallState<E>()
    object Loading:ApiCallState<Nothing>()
}