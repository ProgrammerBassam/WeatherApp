/*
 * *
 *  * Created by Bassam Abdulrazzaq on 8/21/23, 10:51 PM
 *  * Copyright (c) 2023 . All rights reserved.
 *  * Last modified 8/20/23, 8:48 PM
 *
 */

package com.bassamapps.weatherapp.core.model



import kotlinx.serialization.Serializable

/**
 * Network error
 *
 * @property error
 * @constructor Create empty Network error
 */
@Serializable
data class NetworkError (
    val error: NetworkErrorBody
)

/**
 * Network error body
 *
 * @property code
 * @property message
 * @constructor Create empty Network error body
 */
@Serializable
data class NetworkErrorBody (
    val code: Int,
    val message: String
)

/**
 * To error data
 *
 * @return
 */
fun NetworkError.toErrorData(): ErrorData {
    val errorBodyData = ErrorBodyData(
        code =  this.error.code,
        message = this.error.message
    )

    return ErrorData(error = errorBodyData)
}