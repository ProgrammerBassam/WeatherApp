/*
 * *
 *  * Created by Bassam Abdulrazzaq on 8/20/23, 2:14 AM
 *  * Copyright (c) 2023 . All rights reserved.
 *  * Last modified 8/19/23, 2:07 AM
 *
 */

package com.bassamapps.weatherapp.core.network.model

import com.bassamapps.weatherapp.core.network.model.util.BooleanSerializer
import com.bassamapps.weatherapp.core.network.model.util.StringIntSerializer
import com.bassamapps.weatherapp.core.network.model.util.StringSerializer
import com.bassamapps.weatherapp.core.network.model.util.StringTimeSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName

@Serializable
data class NetworkForecastDay (
    val date      : String,
    @SerialName("date_epoch")
    val dateEpoch : Double,
    val day       : NetworkDay,
    val astro     : NetworkAstro,
    val hour      : ArrayList<NetworkHour>
)

@Serializable
data class NetworkDay (
    @Serializable(StringSerializer::class)
    @SerialName("maxtemp_c")
    val maxTempC          : String,
    @SerialName("maxtemp_f")
    @Serializable(StringSerializer::class)
    val maxTempF          : String,
    @SerialName("mintemp_c")
    @Serializable(StringSerializer::class)
    val minTempC         : String,
    @SerialName("mintemp_f")
    @Serializable(StringSerializer::class)
    val minTempF          : String,
    @SerialName("avgtemp_c")
    val avgTempC          : Double,
    @SerialName("avgtemp_f")
    val avgTempF          : Double,
    @SerialName("maxwind_mph")
    val maxWindMph        : Double,
    @SerialName("maxwind_kph")
    val maxWindKph        : Double,
    @SerialName("totalprecip_mm")
    val totalPrecipMm     : Double,
    @SerialName("totalprecip_in")
    val totalPrecipIn     : Double,
    @SerialName("totalsnow_cm")
    val totalSnowCm       : Double,
    @SerialName("avgvis_km")
    val avgVisKm          : Double,
    @SerialName("avgvis_miles")
    val avgVisMiles       : Double,
    @SerialName("avghumidity")
    @Serializable(StringSerializer::class)
    val avgHumidity       : String,
    @SerialName("daily_will_it_rain")
    val dailyWillItRain   : Double,
    @SerialName("daily_chance_of_rain")
    val dailyChanceOfRain : Double,
    @SerialName("daily_will_it_snow")
    val dailyWillItSnow   : Double,
    @SerialName("daily_chance_of_snow")
    val dailyChanceOfSnow : Double,
    val condition         : NetworkCondition,
    val uv                : Double
)

@Serializable
data class NetworkAstro(
    val sunrise          : String,
    val sunset           : String,
    val moonrise         : String,
    val moonset          : String,
    @SerialName("moon_phase")
    val moonPhase        : String,
    @SerialName("moon_illumination")
    val moonIllumination : String,
    @SerialName("is_moon_up")
    val isMoonUp         : Double,
    @SerialName("is_sun_up")
    val isSunUp          : Double
)

@Serializable
data class NetworkHour(
    @SerialName("time_epoch")
    @Serializable(StringTimeSerializer::class)
    val timeEpoch    : String,
    val time         : String,
    @SerialName("temp_c")
    @Serializable(StringSerializer::class)
    val tempC        : String,
    @SerialName("temp_f")
    @Serializable(StringSerializer::class)
    val tempF        : String,
    @SerialName("is_day")
    @Serializable(BooleanSerializer::class)
    val isDay            : Boolean,
    val condition    : NetworkCondition,
    @SerialName("wind_mph")
    val windMph      : Double,
    @SerialName("wind_kph")
    val windKph      : Double,
    @SerialName("wind_degree")
    val windDegree   : Double,
    @SerialName("wind_dir")
    val windDir      : String,
    @SerialName("pressure_mb")
    val pressureMb   : Double,
    @SerialName("pressure_in")
    val pressureIn   : Double,
    @SerialName("precip_mm")
    val precipMm     : Double,
    @SerialName("precip_in")
    val precipIn     : Double,
    val humidity     : Double,
    val cloud        : Double,
    @SerialName("feelslike_c")
    val feelsLikeC   : Double,
    @SerialName("feelslike_f")
    val feelsLikeF   : Double,
    @SerialName("windchill_c")
    val windChillC   : Double,
    @SerialName("windchill_f")
    val windChillF   : Double,
    @SerialName("heatindex_c")
    val heatIndexC   : Double,
    @SerialName("heatindex_f")
    val heatIndexF   : Double,
    @SerialName("dewpoint_c")
    val dewPointC    : Double,
    @SerialName("dewpoint_f")
    val dewPointF    : Double,
    @SerialName("will_it_rain")
    val willItRain   : Double,
    @SerialName("chance_of_rain")
    val chanceOfRain : Double,
    @SerialName("will_it_snow")
    val willItSnow   : Double,
    @SerialName("chance_of_snow")
    val chanceOfSnow : Double,
    @SerialName("vis_km")
    val visKm        : Double,
    @SerialName("vis_miles")
    val visMiles     : Double,
    @SerialName("gust_mph")
    val gustMph      : Double,
    @SerialName("gust_kph")
    val gustKph      : Double,
    val uv           : Double
)