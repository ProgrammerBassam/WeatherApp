/*
 * *
 *  * Created by Bassam Abdulrazzaq on 8/20/23, 2:14 AM
 *  * Copyright (c) 2023 . All rights reserved.
 *  * Last modified 8/19/23, 8:29 PM
 *
 */

package com.bassamapps.weatherapp.core.data.util.location

import kotlinx.coroutines.flow.Flow

/**
 * Utility for reporting app gps status
 */
interface GpsMonitor {
   // val isGpsEnabled: Flow<Boolean>

    val isLocationPermissionGranted: Flow<Boolean>
}