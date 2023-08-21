/*
 * *
 *  * Created by Bassam Abdulrazzaq on 8/20/23, 2:14 AM
 *  * Copyright (c) 2023 . All rights reserved.
 *  * Last modified 8/20/23, 12:22 AM
 *
 */

package com.bassamapps.weatherapp.core.result

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map


sealed class Result<out R> {
    data class Success<out T>(val data: T) : Result<T>()
    data class Loading<out T>(val loading: Boolean) : Result<T>()
    data class Error<out T>(val message: String,val code:Int) : Result<T>()
}

interface Mapper<R,E>{
    fun mapFromApiResponse(type:R):E
}

fun<R,E> mapFromApiResponse(result: Flow<Result<R>>, mapper: Mapper<R, E>): Flow<Result<E>> {
    return result.map {
        when(it){
            is Result.Success-> Result.Success(mapper.mapFromApiResponse(it.data))
            is Result.Error->Result.Error(it.message,it.code)
            is Result.Loading -> Result.Loading(it.loading)
        }
    }
}

/* fun <T> Flow<T>.asResult(): Flow<NetworkResponse<T>> {
    return this
        .map { response ->
            if (response is NetworkError) {
                NetworkResponse.Error(response as NetworkError)
            } else {
                NetworkResponse.Success(response)
            }
        }
        .onStart { emit(NetworkResponse.Loading) }

        .retryWhen { cause, attempt ->
            if (cause is IOException && attempt < RETRY_ATTEMPT_COUNT) {
                delay(RETRY_TIME_IN_MILLIS)
                true
            } else {
                false
            }
        }
        .catch {
            Log.e("ininininv catch", it.toString())
            emit(NetworkResponse.Exception(it))
        }
}
*/