/*
 * *
 *  * Created by Bassam Abdulrazzaq on 8/21/23, 10:51 PM
 *  * Copyright (c) 2023 . All rights reserved.
 *  * Last modified 8/20/23, 2:14 AM
 *
 */

package com.bassamapps.weatherapp.core.ui

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter

/**
 * Time zone broadcast receiver
 *
 * @property onTimeZoneChanged
 * @constructor Create empty Time zone broadcast receiver
 */
class TimeZoneBroadcastReceiver(
    val onTimeZoneChanged: () -> Unit,
) : BroadcastReceiver() {
    private var registered = false

    override fun onReceive(context: Context, intent: Intent) {
        if (intent.action == Intent.ACTION_TIMEZONE_CHANGED) {
            onTimeZoneChanged()
        }
    }

    /**
     * Register
     *
     * @param context
     */
    fun register(context: Context) {
        if (!registered) {
            val filter = IntentFilter()
            filter.addAction(Intent.ACTION_TIMEZONE_CHANGED)
            context.registerReceiver(this, filter)
            registered = true
        }
    }

    /**
     * Unregister
     *
     * @param context
     */
    fun unregister(context: Context) {
        if (registered) {
            context.unregisterReceiver(this)
            registered = false
        }
    }
}
