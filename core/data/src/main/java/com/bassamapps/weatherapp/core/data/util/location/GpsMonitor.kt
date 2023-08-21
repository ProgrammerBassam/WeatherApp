/*
 * *
 *  * Created by Bassam Abdulrazzaq on 8/21/23, 10:51 PM
 *  * Copyright (c) 2023 . All rights reserved.
 *  * Last modified 8/20/23, 2:14 AM
 *
 */

package com.bassamapps.weatherapp.core.data.util.location

import kotlinx.coroutines.flow.Flow

/**
 * Gps monitor
 *
 * @constructor Create empty Gps monitor
 */
interface GpsMonitor {
   // val isGpsEnabled: Flow<Boolean>

    val isLocationPermissionGranted: Flow<Boolean>
}