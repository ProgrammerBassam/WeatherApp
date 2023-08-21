/*
 * *
 *  * Created by Bassam Abdulrazzaq on 8/20/23, 2:14 AM
 *  * Copyright (c) 2023 . All rights reserved.
 *  * Last modified 8/17/23, 5:35 PM
 *
 */

package com.bassamapps.weatherapp.core.network.model

import coil.map.Mapper
import com.bassamapps.weatherapp.core.model.data.WeatherData
import com.bassamapps.weatherapp.core.model.data.WeatherHeadData
import kotlinx.serialization.Serializable



@Serializable
data class NetworkWeather (
    val location : NetworkLocation,
    val current  : NetworkCurrent,
    val forecast : NetworkForecast
)