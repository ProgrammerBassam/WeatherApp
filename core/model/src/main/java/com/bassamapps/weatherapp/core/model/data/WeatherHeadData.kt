/*
 * *
 *  * Created by Bassam Abdulrazzaq on 8/20/23, 2:14 AM
 *  * Copyright (c) 2023 . All rights reserved.
 *  * Last modified 8/17/23, 3:18 PM
 *
 */

package com.bassamapps.weatherapp.core.model.data

data class WeatherHeadData (
    val currentDeg: String,
    val highDeg: String,
    val lowDeg: String,
    val description: String,
    val icon: String
)