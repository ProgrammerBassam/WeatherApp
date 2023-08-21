/*
 * *
 *  * Created by Bassam Abdulrazzaq on 8/20/23, 2:14 AM
 *  * Copyright (c) 2023 . All rights reserved.
 *  * Last modified 8/16/23, 7:25 PM
 *
 */

package com.bassamapps.weatherapp.core.model


data class ErrorData (
    val error: ErrorBodyData
)

data class ErrorBodyData (
    val code: Int,
    val message: String
)