/*
 * *
 *  * Created by Bassam Abdulrazzaq on 8/20/23, 2:14 AM
 *  * Copyright (c) 2023 . All rights reserved.
 *  * Last modified 8/17/23, 3:31 PM
 *
 */

package com.bassamapps.weatherapp.core.network.model

import com.bassamapps.weatherapp.core.model.data.LocationData
import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName

/**
 * Network representation of [LocationData]
 */
@Serializable
class NetworkLocation (
    val name           : String,
    val region         : String,
    val country        : String,
    val lat            : Double,
    val lon            : Double,
    @SerialName("tz_id")
    val tzId           : String,
    @SerialName("localtime_epoch")
    val localtimeEpoch : Double,
    val localtime      : String
)