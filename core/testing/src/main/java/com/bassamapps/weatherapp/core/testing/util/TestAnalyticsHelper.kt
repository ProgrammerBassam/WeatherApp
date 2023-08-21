/*
 * *
 *  * Created by Bassam Abdulrazzaq on 8/20/23, 2:14 AM
 *  * Copyright (c) 2023 . All rights reserved.
 *  * Last modified 8/16/23, 12:13 AM
 *
 */

package com.bassamapps.weatherapp.core.testing.util

import com.bassamapps.weatherapp.core.analytics.AnalyticsEvent
import com.bassamapps.weatherapp.core.analytics.AnalyticsHelper

class TestAnalyticsHelper : AnalyticsHelper {

    private val events = mutableListOf<AnalyticsEvent>()
    override fun logEvent(event: AnalyticsEvent) {
        events.add(event)
    }

    fun hasLogged(event: AnalyticsEvent) = events.contains(event)
}