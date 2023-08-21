/*
 * *
 *  * Created by Bassam Abdulrazzaq on 8/20/23, 2:14 AM
 *  * Copyright (c) 2023 . All rights reserved.
 *  * Last modified 8/14/23, 8:04 PM
 *
 */

package com.bassamapps.weatherapp.core.data.repository

import  com.bassamapps.weatherapp.core.analytics.AnalyticsEvent
import  com.bassamapps.weatherapp.core.analytics.AnalyticsEvent.Param
import  com.bassamapps.weatherapp.core.analytics.AnalyticsHelper

fun AnalyticsHelper.logThemeChanged(themeName: String) =
    logEvent(
        AnalyticsEvent(
            type = "theme_changed",
            extras = listOf(
                Param(key = "theme_name", value = themeName),
            ),
        ),
    )

fun AnalyticsHelper.logDarkThemeConfigChanged(darkThemeConfigName: String) =
    logEvent(
        AnalyticsEvent(
            type = "dark_theme_config_changed",
            extras = listOf(
                Param(key = "dark_theme_config", value = darkThemeConfigName),
            ),
        ),
    )

fun AnalyticsHelper.logDynamicColorPreferenceChanged(useDynamicColor: Boolean) =
    logEvent(
        AnalyticsEvent(
            type = "dynamic_color_preference_changed",
            extras = listOf(
                Param(key = "dynamic_color_preference", value = useDynamicColor.toString()),
            ),
        ),
    )