/*
 * *
 *  * Created by Bassam Abdulrazzaq on 8/21/23, 10:51 PM
 *  * Copyright (c) 2023 . All rights reserved.
 *  * Last modified 8/20/23, 2:43 AM
 *
 */

package com.bassamapps.weatherapp.core.data.model



data class WeatherResult (
    val location : LocationResult,
    val current  : CurrentResult,
    val forecast  : ForecastResult
)

