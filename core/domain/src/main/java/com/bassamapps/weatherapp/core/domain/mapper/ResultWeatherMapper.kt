/*
 * *
 *  * Created by Bassam Abdulrazzaq on 8/21/23, 10:51 PM
 *  * Copyright (c) 2023 . All rights reserved.
 *  * Last modified 8/21/23, 9:56 PM
 *
 */

package com.bassamapps.weatherapp.core.domain.mapper

import android.content.Context
import android.text.format.DateFormat
import android.util.Log
import com.bassamapps.weatherapp.core.data.model.DayResult
import com.bassamapps.weatherapp.core.data.model.HourResult
import com.bassamapps.weatherapp.core.data.model.WeatherResult
import com.bassamapps.weatherapp.core.domain.R
import com.bassamapps.weatherapp.core.model.data.FutureWeatherData
import com.bassamapps.weatherapp.core.model.data.HourData
import com.bassamapps.weatherapp.core.model.data.UserData
import com.bassamapps.weatherapp.core.model.data.WeatherData
import com.bassamapps.weatherapp.core.model.data.WeatherExtraData
import com.bassamapps.weatherapp.core.model.data.WeatherFooterData
import com.bassamapps.weatherapp.core.model.data.WeatherForecastHourData
import com.bassamapps.weatherapp.core.model.data.WeatherHeadData
import com.bassamapps.weatherapp.core.strings.ResourcesProvider
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.StateFlow
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale
import javax.inject.Inject

/**
 * Result weather mapper
 *
 * @property context
 * @property resourcesProvider
 * @constructor Create empty Result weather mapper
 */
class ResultWeatherMapper @Inject constructor(
    @ApplicationContext private val context: Context,
    private val resourcesProvider: ResourcesProvider,
)  {


    /**
     * Map from api response
     *
     * @param data
     * @param us
     * @return
     */
    fun mapFromApiResponse(data: WeatherResult, us: StateFlow<UserData>): WeatherData {
         val userData = us.value
         Log.e("Mapppper ", userData.isTempC.toString())
        return WeatherData(
            locationName = data.location.name,
            weatherHeadData = WeatherHeadData(
                currentDeg = if(userData.isTempC) data.current.tempC else data.current.tempF,
                highDeg = if(userData.isTempC) data.forecast.forecastday[0].day.maxTempC else data.forecast.forecastday[0].day.maxTempF,
                lowDeg = if(userData.isTempC) data.forecast.forecastday[0].day.minTempC else data.forecast.forecastday[0].day.minTempF,
                description = data.current.condition.text,
                icon = "https:" + data.current.condition.icon
            ),
            weatherForecastHourData = WeatherForecastHourData(
                description = data.forecast.forecastday[0].day.condition.text,
                type = if (data.current.isDay) resourcesProvider.getString(R.string.high) else resourcesProvider.getString(
                    R.string.low),
                degree = getDegreeAccordingToDayTypeAndDegree(data.current.isDay, userData.isTempC, data.forecast.forecastday[0].day) ,
                degreeType = if (userData.isTempC) resourcesProvider.getString(R.string.c_temp) else resourcesProvider.getString(
                    R.string.f_temp),
                hours = data.forecast.forecastday[0].hour.map {
                    HourData(
                        hour = it.timeEpoch,
                        icon = "https:" + it.condition.icon,
                        degree = if(userData.isTempC) it.tempC else it.tempF
                    )
                }
            ),
            extra = WeatherExtraData(
                uv = getUvValue(data.current.uv.toDouble()),
                humidity = data.current.humidity,
                wind = "${data.current.windKph} ${resourcesProvider.getString(R.string.km_per_h)}",
                sunrise = data.forecast.forecastday[0].astro.sunrise.checkLocalTimeSystem(),
                sunset = data.forecast.forecastday[0].astro.sunset.checkLocalTimeSystem()
            ),
            futureData = data.forecast.forecastday.map {
                FutureWeatherData(
                    dayName = it.date.getDayOfWeek(),
                    humidity = it.day.avgHumidity,
                    dayIcon = it.hour.getIcon(isDay = true, isTemC = userData.isTempC),
                    nightIcon = it.hour.getIcon(isDay = false, isTemC = userData.isTempC),
                    highDeg = if(userData.isTempC) it.day.maxTempC else it.day.maxTempF,
                    lowDeg = if(userData.isTempC) it.day.minTempC else it.day.minTempF
                )
            },
            weatherFooterData = WeatherFooterData(
                lastUpdateTime = data.current.lastUpdated
            )
        )
    }

    private fun getDegreeAccordingToDayTypeAndDegree(
        isDay: Boolean,
        isTempC: Boolean,
        day: DayResult, ) : String {

        var degree = ""

        degree = if (isDay) {
            if (isTempC) {
                day.maxTempC
            } else {
                day.maxTempF
            }
        } else {
            if (isTempC) {
                day.minTempC
            } else {
                day.minTempF
            }
        }

        return degree
    }

    private fun String.getDayOfWeek(): String {
        val format = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        val date = format.parse(this) ?: return ""

        val calendar = Calendar.getInstance()
        calendar.time = date

        // Mapping day of week value to the corresponding name
        return when (calendar.get(Calendar.DAY_OF_WEEK)) {
            Calendar.SUNDAY -> resourcesProvider.getString(R.string.sunday)
            Calendar.MONDAY -> resourcesProvider.getString(R.string.monday)
            Calendar.TUESDAY -> resourcesProvider.getString(R.string.tuesday)
            Calendar.WEDNESDAY -> resourcesProvider.getString(R.string.wednesday)
            Calendar.THURSDAY -> resourcesProvider.getString(R.string.thursday)
            Calendar.FRIDAY -> resourcesProvider.getString(R.string.friday)
            Calendar.SATURDAY -> resourcesProvider.getString(R.string.saturday)
            else -> ""
        }
    }

    private fun List<HourResult>.getIcon(isDay: Boolean, isTemC: Boolean): String {

        var icon = ""
        var deg = -10000000.0

        this.map {
            val tempC = it.tempC.toDouble()
            val tempF = it.tempF.toDouble()

            if (isDay && it.isDay) {
                if (isTemC && tempC > deg) {
                    deg = tempC;
                } else if (!isTemC && tempF > deg) {
                    deg = tempF;
                }

                icon = "https:" + it.condition.icon
            } else if (!isDay && !it.isDay) {
                if (isTemC && tempC < deg) {
                    deg = tempC;
                } else if (!isTemC && tempF < deg) {
                    deg = tempF;
                }

                icon = "https:" + it.condition.icon
            }
        }

        return icon
    }

    private fun String.checkLocalTimeSystem(): String {
        val is24HourFormat = DateFormat.is24HourFormat(context)

        return if (is24HourFormat) {
            val inputFormat = SimpleDateFormat("hh:mm a", Locale.getDefault())
            val outputFormat = SimpleDateFormat("HH:mm", Locale.getDefault())

            val date = inputFormat.parse(this)

            outputFormat.format(date)
        } else {
            this
        }
    }

    private fun getUvValue(uv: Double): String {
        return if (uv >= 0 && uv < 3) {
            resourcesProvider.getString(R.string.low)
        } else if (uv >= 3 && uv < 5) {
            resourcesProvider.getString(R.string.moderate)
        } else {
            resourcesProvider.getString(R.string.high)
        }
    }



}
