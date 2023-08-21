/*
 * *
 *  * Created by Bassam Abdulrazzaq on 8/21/23, 10:51 PM
 *  * Copyright (c) 2023 . All rights reserved.
 *  * Last modified 8/20/23, 2:14 AM
 *
 */

package com.bassamapps.weatherapp.core.network.model

import kotlinx.serialization.Serializable

/**
 * Network forecast
 *
 * @property forecastday
 * @constructor Create empty Network forecast
 */
@Serializable
data class NetworkForecast (
    val forecastday: ArrayList<NetworkForecastDay>
)