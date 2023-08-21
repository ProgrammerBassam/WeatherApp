/*
 * *
 *  * Created by Bassam Abdulrazzaq on 8/20/23, 2:14 AM
 *  * Copyright (c) 2023 . All rights reserved.
 *  * Last modified 8/14/23, 7:17 PM
 *
 */

package com.bassamapps.weatherapp.core.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import com.bassamapps.weatherapp.core.analytics.AnalyticsEvent
import com.bassamapps.weatherapp.core.analytics.AnalyticsEvent.Param
import com.bassamapps.weatherapp.core.analytics.AnalyticsEvent.ParamKeys
import com.bassamapps.weatherapp.core.analytics.AnalyticsEvent.Types
import com.bassamapps.weatherapp.core.analytics.AnalyticsHelper
import com.bassamapps.weatherapp.core.analytics.LocalAnalyticsHelper

/**
 * Classes and functions associated with analytics events for the UI.
 */
fun AnalyticsHelper.logScreenView(screenName: String) {
    logEvent(
        AnalyticsEvent(
            type = Types.SCREEN_VIEW,
            extras = listOf(
                Param(ParamKeys.SCREEN_NAME, screenName),
            ),
        ),
    )
}

fun AnalyticsHelper.logNewsResourceOpened(newsResourceId: String) {
    logEvent(
        event = AnalyticsEvent(
            type = "news_resource_opened",
            extras = listOf(
                Param("opened_news_resource", newsResourceId),
            ),
        ),
    )
}

/**
 * A side-effect which records a screen view event.
 */
@Composable
fun TrackScreenViewEvent(
    screenName: String,
    analyticsHelper: AnalyticsHelper = LocalAnalyticsHelper.current,
) = DisposableEffect(Unit) {
    analyticsHelper.logScreenView(screenName)
    onDispose {}
}
