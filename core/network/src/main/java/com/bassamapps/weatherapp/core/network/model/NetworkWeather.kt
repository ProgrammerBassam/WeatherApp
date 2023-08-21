/*
 * *
 *  * Created by Bassam Abdulrazzaq on 8/21/23, 10:51 PM
 *  * Copyright (c) 2023 . All rights reserved.
 *  * Last modified 8/20/23, 2:14 AM
 *
 */

package com.bassamapps.weatherapp.core.network.model

import coil.map.Mapper
import com.bassamapps.weatherapp.core.model.data.WeatherData
import com.bassamapps.weatherapp.core.model.data.WeatherHeadData
import kotlinx.serialization.Serializable


/**
 * Network weather
 *
 * @property location
 * @property current
 * @property forecast
 * @constructor Create empty Network weather
 */
@Serializable
data class NetworkWeather (
    val location : NetworkLocation,
    val current  : NetworkCurrent,
    val forecast : NetworkForecast
)