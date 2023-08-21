/*
 * *
 *  * Created by Bassam Abdulrazzaq on 8/20/23, 2:14 AM
 *  * Copyright (c) 2023 . All rights reserved.
 *  * Last modified 8/14/23, 7:17 PM
 *
 */

package com.bassamapps.weatherapp.core.analytics

import android.util.Log
import javax.inject.Inject
import javax.inject.Singleton

private const val TAG = "StubAnalyticsHelper"

/**
 * An implementation of AnalyticsHelper just writes the events to logcat. Used in builds where no
 * analytics events should be sent to a backend.
 */
@Singleton
class StubAnalyticsHelper @Inject constructor() : AnalyticsHelper {
    override fun logEvent(event: AnalyticsEvent) {
        Log.d(TAG, "Received analytics event: $event")
    }
}
