/*
 * *
 *  * Created by Bassam Abdulrazzaq on 8/20/23, 2:14 AM
 *  * Copyright (c) 2023 . All rights reserved.
 *  * Last modified 8/17/23, 8:59 PM
 *
 */

package com.bassamapps.weatherapp.core.model.data

data class WeatherForecastHourData (
    val description: String,
    val type: String,
    val degree: String,
    val degreeType: String,
    val hours: List<HourData>,
)

data class HourData(
    val hour: String,
    val icon: String,
    val degree: String
)