/*
 * *
 *  * Created by Bassam Abdulrazzaq on 8/20/23, 2:14 AM
 *  * Copyright (c) 2023 . All rights reserved.
 *  * Last modified 8/17/23, 7:17 PM
 *
 */

package com.bassamapps.weatherapp.core.model



import kotlinx.serialization.Serializable

@Serializable
data class NetworkError (
    val error: NetworkErrorBody
)

@Serializable
data class NetworkErrorBody (
    val code: Int,
    val message: String
)

fun NetworkError.toErrorData(): ErrorData {
    val errorBodyData = ErrorBodyData(
        code =  this.error.code,
        message = this.error.message
    )

    return ErrorData(error = errorBodyData)
}