/*
 * *
 *  * Created by Bassam Abdulrazzaq on 8/20/23, 2:14 AM
 *  * Copyright (c) 2023 . All rights reserved.
 *  * Last modified 8/17/23, 4:59 PM
 *
 */

package com.bassamapps.weatherapp.core.data.model



data class WeatherResult (
    val location : LocationResult,
    val current  : CurrentResult,
    val forecast  : ForecastResult
)

