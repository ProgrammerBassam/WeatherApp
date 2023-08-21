/*
 * *
 *  * Created by Bassam Abdulrazzaq on 8/20/23, 2:14 AM
 *  * Copyright (c) 2023 . All rights reserved.
 *  * Last modified 8/20/23, 1:16 AM
 *
 */

package com.bassamapps.weatherapp.core.model.data

data class WeatherData(
    val locationName: String,
    val weatherHeadData: WeatherHeadData,
    val weatherForecastHourData: WeatherForecastHourData,
    val extra: WeatherExtraData,
    val futureData: List<FutureWeatherData>,
    val weatherFooterData: WeatherFooterData,
)


