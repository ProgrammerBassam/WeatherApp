/*
 * *
 *  * Created by Bassam Abdulrazzaq on 8/21/23, 10:51 PM
 *  * Copyright (c) 2023 . All rights reserved.
 *  * Last modified 8/20/23, 2:14 AM
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