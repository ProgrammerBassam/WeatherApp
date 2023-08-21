/*
 * *
 *  * Created by Bassam Abdulrazzaq on 8/21/23, 10:51 PM
 *  * Copyright (c) 2023 . All rights reserved.
 *  * Last modified 8/20/23, 2:14 AM
 *
 */

package com.bassamapps.weatherapp.core.model


/**
 * Error data
 *
 * @property error
 * @constructor Create empty Error data
 */
data class ErrorData (
    val error: ErrorBodyData
)

/**
 * Error body data
 *
 * @property code
 * @property message
 * @constructor Create empty Error body data
 */
data class ErrorBodyData (
    val code: Int,
    val message: String
)