/*
 * *
 *  * Created by Bassam Abdulrazzaq on 8/20/23, 2:14 AM
 *  * Copyright (c) 2023 . All rights reserved.
 *  * Last modified 8/16/23, 6:53 PM
 *
 */

package com.bassamapps.weatherapp.core.model.data

data class CurrentData (
    val last_updated_epoch : Double?,
    val last_updated      : String?,
    val temp_c            : Double?,
    val temp_f            : Double?,
    val is_day            : Double?,
    val condition        : ConditionData?,
    val wind_mph          : Double?,
    val wind_kph          : Double?,
    val wind_degree       : Double?,
    val wind_dir          : String?,
    val pressure_mb       : Double?,
    val pressure_in       : Double?,
    val precip_mm         : Double?,
    val precip_in         : Double?,
    val humidity         : Double?,
    val cloud            : Double?,
    val feelslike_c       : Double?,
    val feelslike_f       : Double?,
    val vis_km            : Double?,
    val vis_miles         : Double?,
    val uv               : Double?,
    val gust_mph          : Double?,
    val gust_kph          : Double?
)