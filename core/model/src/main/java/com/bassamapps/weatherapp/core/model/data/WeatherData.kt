/*
 * *
 *  * Created by Bassam Abdulrazzaq on 8/21/23, 10:51 PM
 *  * Copyright (c) 2023 . All rights reserved.
 *  * Last modified 8/21/23, 6:30 PM
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


