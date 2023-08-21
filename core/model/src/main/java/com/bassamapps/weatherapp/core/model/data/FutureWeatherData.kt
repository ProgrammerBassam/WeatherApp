/*
 * *
 *  * Created by Bassam Abdulrazzaq on 8/20/23, 2:14 AM
 *  * Copyright (c) 2023 . All rights reserved.
 *  * Last modified 8/19/23, 1:14 AM
 *
 */

package com.bassamapps.weatherapp.core.model.data

data class FutureWeatherData (
    val dayName: String,
    val humidity: String,
    val dayIcon: String,
    val nightIcon: String,
    val highDeg: String,
    val lowDeg: String,
)