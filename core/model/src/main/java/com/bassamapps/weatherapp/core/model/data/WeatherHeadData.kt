/*
 * *
 *  * Created by Bassam Abdulrazzaq on 8/21/23, 10:51 PM
 *  * Copyright (c) 2023 . All rights reserved.
 *  * Last modified 8/20/23, 2:14 AM
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