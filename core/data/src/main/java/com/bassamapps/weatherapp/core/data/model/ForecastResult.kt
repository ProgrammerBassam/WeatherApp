/*
 * *
 *  * Created by Bassam Abdulrazzaq on 8/20/23, 2:14 AM
 *  * Copyright (c) 2023 . All rights reserved.
 *  * Last modified 8/19/23, 2:07 AM
 *
 */

package com.bassamapps.weatherapp.core.data.model

data class ForecastResult(
    val forecastday: List<ForecastDayResult>
)

data class ForecastDayResult(
    val date: String,
    val dateEpoch: Double,
    val day: DayResult,
    val astro: AstroResult,
    val hour: List<HourResult>
)

data class DayResult(
    val maxTempC          : String,
    val maxTempF          : String,
    val minTempC         : String,
    val minTempF          : String,
    val avgHumidity          : String,
    val condition         : ConditionResult,
)

data class AstroResult(
    val sunrise          : String,
    val sunset           : String,
    val moonrise         : String,
    val moonset          : String,
    val moonPhase        : String,
    val moonIllumination : String,
    val isMoonUp         : Double,
    val isSunUp          : Double
)

data class HourResult(
    val timeEpoch    : String,
    val time         : String,
    val tempC        : String,
    val tempF        : String,
    val isDay        : Boolean,
    val condition    : ConditionResult,
)