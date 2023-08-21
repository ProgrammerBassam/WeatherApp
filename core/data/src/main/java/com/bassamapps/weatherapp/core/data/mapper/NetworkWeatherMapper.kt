/*
 * *
 *  * Created by Bassam Abdulrazzaq on 8/21/23, 4:45 PM
 *  * Copyright (c) 2023 . All rights reserved.
 *  * Last modified 8/21/23, 4:45 PM
 *
 */

package com.bassamapps.weatherapp.core.data.mapper

import com.bassamapps.weatherapp.core.data.model.AstroResult
import com.bassamapps.weatherapp.core.data.model.ConditionResult
import com.bassamapps.weatherapp.core.data.model.CurrentResult
import com.bassamapps.weatherapp.core.data.model.DayResult
import com.bassamapps.weatherapp.core.data.model.ForecastDayResult
import com.bassamapps.weatherapp.core.data.model.ForecastResult
import com.bassamapps.weatherapp.core.data.model.HourResult
import com.bassamapps.weatherapp.core.data.model.LocationResult
import com.bassamapps.weatherapp.core.data.model.WeatherResult
import com.bassamapps.weatherapp.core.network.model.NetworkWeather
import com.bassamapps.weatherapp.core.network.utils.Mapper
import javax.inject.Inject

class NetworkWeatherMapper @Inject constructor() : Mapper<NetworkWeather, WeatherResult> {
    override fun mapFromApiResponse(data: NetworkWeather): WeatherResult {
        return WeatherResult(
            location = LocationResult(
                name = data.location.name,
                localtimeEpoch = data.location.localtimeEpoch,
                localtime = data.location.localtime
            ),
            current = CurrentResult(
                lastUpdatedEpoch = data.current.lastUpdatedEpoch,
                lastUpdated = data.current.lastUpdated,
                tempC = data.current.tempC,
                tempF = data.current.tempF,
                isDay = data.current.isDay,
                condition = ConditionResult(
                    text = data.current.condition.text,
                    icon = data.current.condition.icon,
                    code = data.current.condition.code,
                ),
                uv = data.current.uv,
                humidity = data.current.humidity,
                windKph = data.current.windKph

            ),
            forecast = ForecastResult(
                forecastday = data.forecast.forecastday.map {
                    ForecastDayResult(
                        date = it.date,
                        dateEpoch = it.dateEpoch,
                        day = DayResult(
                            maxTempC = it.day.maxTempC,
                            maxTempF = it.day.maxTempF,
                            minTempC = it.day.minTempC,
                            minTempF = it.day.minTempF,
                            condition = ConditionResult(
                                text = it.day.condition.text,
                                icon = it.day.condition.icon,
                                code = it.day.condition.code,
                            ),
                            avgHumidity = it.day.avgHumidity
                        ),
                        astro = AstroResult(
                            sunrise = it.astro.sunrise,
                            sunset = it.astro.sunset,
                            moonrise = it.astro.moonrise,
                            moonset = it.astro.moonset,
                            moonPhase = it.astro.moonPhase,
                            moonIllumination = it.astro.moonIllumination,
                            isMoonUp = it.astro.isMoonUp,
                            isSunUp = it.astro.isSunUp
                        ),
                        hour = it.hour.map { currentH ->
                            HourResult(
                                timeEpoch = currentH.timeEpoch,
                                time = currentH.time,
                                tempC = currentH.tempC,
                                tempF = currentH.tempF,
                                isDay = currentH.isDay,
                                condition = ConditionResult(
                                    text = currentH.condition.text,
                                    icon = currentH.condition.icon,
                                    code = currentH.condition.code,
                                ),
                            )
                        }
                    )
                }
            )
        )
    }
}