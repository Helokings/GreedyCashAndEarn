package com.example.networking.utils

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import retrofit2.Response

fun <T> result(Response:suspend ()->Response<T>):Flow<ApiCallState<T>> = flow {
    emit(ApiCallState.Loading)
    val c = Response()
    c?.let {
        if (it.isSuccessful){
            if (c.body()!=null){
            emit(ApiCallState.Success(c.body()!!))
            }
        }else{
            c.errorBody()?.let { error ->
                error.close()
                emit(ApiCallState.Error(error.string() as T))
            }
        }
    }

}.catch { error->
    emit(ApiCallState.Error(error.message as T))
}