/*
 * *
 *  * Created by Bassam Abdulrazzaq on 8/20/23, 2:45 AM
 *  * Copyright (c) 2023 . All rights reserved.
 *  * Last modified 8/20/23, 2:45 AM
 *
 */

package com.bassamapps.weatherapp.core.model.data

sealed interface DataResult<out T> {
    data class Success<T>(val data: T) : DataResult<T>
    data class Error(val exception: Throwable? = null) : DataResult<Nothing>
}


