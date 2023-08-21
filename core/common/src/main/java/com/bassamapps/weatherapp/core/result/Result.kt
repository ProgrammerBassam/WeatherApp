/*
 * *
 *  * Created by Bassam Abdulrazzaq on 8/21/23, 10:51 PM
 *  * Copyright (c) 2023 . All rights reserved.
 *  * Last modified 8/21/23, 10:25 PM
 *
 */

package com.bassamapps.weatherapp.core.result

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart


/**
 * Result
 *
 * @param R
 * @constructor Create empty Result
 */
sealed class Result<out R> {
    /**
     * Success
     *
     * @param T
     * @property data
     * @constructor Create empty Success
     */
    data class Success<out T>(val data: T) : Result<T>()

    /**
     * Loading
     *
     * @param T
     * @property loading
     * @constructor Create empty Loading
     */
    data class Loading<out T>(val loading: Boolean) : Result<T>()

    /**
     * Error
     *
     * @param T
     * @property message
     * @property code
     * @constructor Create empty Error
     */
    data class Error<out T>(val message: String, val code:Int) : Result<T>()
}

/**
 * Mapper
 *
 * @param R
 * @param E
 * @constructor Create empty Mapper
 */
interface Mapper<R,E>{
    /**
     * Map from api response
     *
     * @param type
     * @return
     */
    fun mapFromApiResponse(type:R):E
}

/**
 * Map from api response
 *
 * @param R
 * @param E
 * @param result
 * @param mapper
 * @return
 */
fun<R,E> mapFromApiResponse(result: Flow<Result<R>>, mapper: Mapper<R, E>): Flow<Result<E>> {
    return result.map {
        when(it){
            is Result.Success-> Result.Success(mapper.mapFromApiResponse(it.data))
            is Result.Error->Result.Error(it.message,it.code)
            is Result.Loading -> Result.Loading(it.loading)
        }
    }
}

/**
 * As result
 *
 * @param T
 * @return
 */
fun <T> Flow<T>.asResult(): Flow<Result<T>> {
    return this
        .map<T, Result<T>> {
            Result.Success(it)
        }
        .onStart { emit(Result.Loading(true)) }
        .catch { emit(Result.Error(message = it.message.toString(), code = 0)) }
}