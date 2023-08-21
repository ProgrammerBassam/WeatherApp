/*
 * *
 *  * Created by Bassam Abdulrazzaq on 8/21/23, 10:51 PM
 *  * Copyright (c) 2023 . All rights reserved.
 *  * Last modified 8/20/23, 2:14 AM
 *
 */

package com.bassamapps.weatherapp.core.data.model

data class CurrentResult (
    val lastUpdatedEpoch : Double,
    val lastUpdated      : String,
    val tempC            : String,
    val tempF            : String,
    val isDay            : Boolean,
    val condition        : ConditionResult,
    val uv               : String,
    val humidity         : String,
    val windKph          : String
)