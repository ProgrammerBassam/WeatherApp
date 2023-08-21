/*
 * *
 *  * Created by Bassam Abdulrazzaq on 8/21/23, 10:51 PM
 *  * Copyright (c) 2023 . All rights reserved.
 *  * Last modified 8/20/23, 2:14 AM
 *
 */

package com.bassamapps.weatherapp.core.data.util

import kotlinx.coroutines.flow.Flow

/**
 * Network monitor
 *
 * @constructor Create empty Network monitor
 */
interface NetworkMonitor {
    val isOnline: Flow<Boolean>
}
