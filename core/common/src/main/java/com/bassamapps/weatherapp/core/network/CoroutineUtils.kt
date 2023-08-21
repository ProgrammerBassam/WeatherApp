/*
 * *
 *  * Created by Bassam Abdulrazzaq on 8/20/23, 2:14 AM
 *  * Copyright (c) 2023 . All rights reserved.
 *  * Last modified 8/16/23, 5:44 PM
 *
 */

package com.bassamapps.weatherapp.core.network

import kotlinx.coroutines.flow.SharingStarted

private const val StopTimeoutMillis: Long = 5000

/**
* A [SharingStarted] meant to be used with a [StateFlow] to expose data to the UI.
*
* When the UI stops observing, upstream flows stay active for some time to allow the system to
* come back from a short-lived configuration change (such as rotations). If the UI stops
* observing for longer, the cache is kept but the upstream flows are stopped. When the UI comes
* back, the latest value is replayed and the upstream flows are executed again. This is done to
* save resources when the app is in the background but let users switch between apps quickly.
*/
val WhileUiSubscribed: SharingStarted = SharingStarted.WhileSubscribed(StopTimeoutMillis)