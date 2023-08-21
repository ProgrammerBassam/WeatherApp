/*
 * *
 *  * Created by Bassam Abdulrazzaq on 8/20/23, 2:14 AM
 *  * Copyright (c) 2023 . All rights reserved.
 *  * Last modified 8/16/23, 6:49 PM
 *
 */

package com.bassamapps.weatherapp.core.model.data

data class LocationData (

    val name           : String,
    val region         : String,
    val country        : String,
    val lat            : Double,
    val lon            : Double,
    val tz_id           : String,
    val localtime_epoch : Int,
    val localtime      : String

)