/*
 * *
 *  * Created by Bassam Abdulrazzaq on 8/20/23, 2:14 AM
 *  * Copyright (c) 2023 . All rights reserved.
 *  * Last modified 8/17/23, 6:14 PM
 *
 */

package com.bassamapps.weatherapp.core.network.model

import kotlinx.serialization.Serializable

@Serializable
data class NetworkForecast (
    val forecastday: ArrayList<NetworkForecastDay>
)