/*
 * *
 *  * Created by Bassam Abdulrazzaq on 8/21/23, 10:51 PM
 *  * Copyright (c) 2023 . All rights reserved.
 *  * Last modified 8/20/23, 2:14 AM
 *
 */

package com.bassamapps.weatherapp.core.network.model

import com.bassamapps.weatherapp.core.model.data.CurrentData
import com.bassamapps.weatherapp.core.network.model.util.BooleanSerializer
import com.bassamapps.weatherapp.core.network.model.util.StringIntSerializer
import com.bassamapps.weatherapp.core.network.model.util.StringSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName

/**
 * Network current
 *
 * @property lastUpdatedEpoch
 * @property lastUpdated
 * @property tempC
 * @property tempF
 * @property isDay
 * @property condition
 * @property windMph
 * @property windKph
 * @property windDegree
 * @property windDir
 * @property pressureMb
 * @property pressureIn
 * @property precipMm
 * @property precipIn
 * @property humidity
 * @property cloud
 * @property feelsLikeC
 * @property feelsLikeF
 * @property visKm
 * @property visMiles
 * @property uv
 * @property gustMph
 * @property gustKph
 * @constructor Create empty Network current
 */
@Serializable
data class NetworkCurrent (
    @SerialName("last_updated_epoch")
    val lastUpdatedEpoch : Double,
    @SerialName("last_updated")
    val lastUpdated      : String,
    @SerialName("temp_c")
    @Serializable(StringSerializer::class)
    val tempC            : String,
    @SerialName("temp_f")
    @Serializable(StringSerializer::class)
    val tempF            : String,
    @SerialName("is_day")
    @Serializable(BooleanSerializer::class)
    val isDay            : Boolean,
    val condition        : NetworkCondition,
    @SerialName("wind_mph")
    val windMph          : Double,
    @SerialName("wind_kph")
    @Serializable(StringSerializer::class)
    val windKph          : String,
    @SerialName("wind_degree")
    val windDegree       : Double,
    @SerialName("wind_dir")
    val windDir          : String,
    @SerialName("pressure_mb")
    val pressureMb       : Double,
    @SerialName("pressure_in")
    val pressureIn       : Double,
    @SerialName("precip_mm")
    val precipMm         : Double,
    @SerialName("precip_in")
    val precipIn         : Double,
    @Serializable(StringIntSerializer::class)
    val humidity         : String,
    val cloud            : Double,
    @SerialName("feelslike_c")
    val feelsLikeC       : Double,
    @SerialName("feelslike_f")
    val feelsLikeF       : Double,
    @SerialName("vis_km")
    val visKm            : Double,
    @SerialName("vis_miles")
    val visMiles         : Double,
    @Serializable(StringSerializer::class)
    val uv               : String,
    @SerialName("gust_mph")
    val gustMph          : Double,
    @SerialName("gust_kph")
    val gustKph          : Double
)