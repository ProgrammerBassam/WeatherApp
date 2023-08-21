/*
 * *
 *  * Created by Bassam Abdulrazzaq on 8/21/23, 10:51 PM
 *  * Copyright (c) 2023 . All rights reserved.
 *  * Last modified 8/20/23, 2:14 AM
 *
 */

package com.bassamapps.weatherapp.di

import android.app.Activity
import android.util.Log
import android.view.Window
import androidx.metrics.performance.JankStats
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

@Module
@InstallIn(ActivityComponent::class)
object JankStatsModule {
    @Provides
    fun providesOnFrameListener(): JankStats.OnFrameListener {
        return JankStats.OnFrameListener { frameData ->
            // Make sure to only log janky frames.
            if (frameData.isJank) {
                // We're currently logging this but would better report it to a backend.
                Log.v("Weather App Jank", frameData.toString())
            }
        }
    }

    @Provides
    fun providesWindow(activity: Activity): Window {
        return activity.window
    }

    @Provides
    fun providesJankStats(
        window: Window,
        frameListener: JankStats.OnFrameListener,
    ): JankStats {
        return JankStats.createAndTrack(window, frameListener)
    }
}
