/*
 * *
 *  * Created by Bassam Abdulrazzaq on 8/20/23, 2:14 AM
 *  * Copyright (c) 2023 . All rights reserved.
 *  * Last modified 8/19/23, 10:11 PM
 *
 */

package com.bassamapps.weatherapp.core.data.util.location

import android.Manifest
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.content.pm.PackageManager
import android.location.GnssStatus
import android.location.GnssStatus.CONSTELLATION_GPS
import android.location.GpsStatus
import android.location.LocationManager
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.channels.SendChannel
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.conflate
import java.util.concurrent.Executors
import javax.inject.Inject

class LocationManagerGpsMonitor @Inject constructor(
    @ApplicationContext private val context: Context
) : GpsMonitor {

  /*  override val isGpsEnabled: Flow<Boolean> = callbackFlow {
        val locationManager = context.getSystemService(Context.LOCATION_SERVICE) as LocationManager


        var callback: GnssStatus.Callback? = null
        var listener: GpsStatus.Listener? = null


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            callback = startProcessForNougatAndElder(locationManager, channel)
        } else {
            listener = startProcessForOlderThanNougat(locationManager, channel)
        }

        /**
         * Sends the latest status status to the underlying channel.
         */
        val gpsProviderEnabled = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)

        channel.trySend(gpsProviderEnabled)

        awaitClose {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N && callback != null) {
                // Unregister the callback when the flow is cancelled
                locationManager.unregisterGnssStatusCallback(callback)
            } else {
                if (listener != null) {
                    // Unregister the listener when the flow is cancelled
                    locationManager.removeGpsStatusListener(listener)
                }
            }
        }
    }
        .conflate()
*/

    override val isLocationPermissionGranted: Flow<Boolean> = callbackFlow {

        val permission = Manifest.permission.ACCESS_COARSE_LOCATION

        // Function to check if the permission is granted
        fun isPermissionGranted(): Boolean {
            val isB = ContextCompat.checkSelfPermission(context, permission) == PackageManager.PERMISSION_GRANTED
            return isB
        }

        // Send the initial permission status
        channel.trySend(isPermissionGranted())

        // Create a broadcast receiver to listen for permission changes
        val receiver = object : BroadcastReceiver() {
            override fun onReceive(context: Context?, intent: Intent?) {
                if (intent?.action == "android.permission.ACCESS_COARSE_LOCATION") {
                    channel.trySend(isPermissionGranted())
                }
            }
        }

        // Create an intent filter for the permission change broadcast
        val filter = IntentFilter().apply {
            addAction("android.permission.ACCESS_COARSE_LOCATION")
        }

        // Register the receiver with the intent filter
        ContextCompat.registerReceiver(context, receiver, filter, ContextCompat.RECEIVER_EXPORTED)

        awaitClose {
            // Unregister the receiver when the flow is cancelled
            context.unregisterReceiver(receiver)
        }

    }
        .conflate()

   /* @RequiresApi(Build.VERSION_CODES.N)
    private fun startProcessForNougatAndElder(
        locationManager: LocationManager,
        channel: SendChannel<Boolean>
    ):  GnssStatus.Callback?  {

        // Create a callback to track GPS provider changes
        val callback = object:  GnssStatus.Callback () {
            override fun onStarted() {
                val gpsProviderEnabled = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)
                channel.trySend(gpsProviderEnabled)
            }

            override fun onStopped() {
                super.onStopped()

                val gpsProviderEnabled = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)
                channel.trySend(gpsProviderEnabled)
            }

            override fun onSatelliteStatusChanged(status: GnssStatus) {
                super.onSatelliteStatusChanged(status)

                val gpsProviderEnabled = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)
                channel.trySend(gpsProviderEnabled)
            }

        }



        // Register the callback with the LocationManager
        if (ActivityCompat.checkSelfPermission(
                context,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
                locationManager.registerGnssStatusCallback(Executors.newSingleThreadExecutor(), callback)
            } else {
                locationManager.registerGnssStatusCallback(callback)
            }

        } else {
            return null
        }


        return callback
    }


    private fun startProcessForOlderThanNougat(
        locationManager: LocationManager,
        channel: SendChannel<Boolean>
    ) : GpsStatus.Listener? {
        // Create a listener to track GPS provider changes
        val gpsProviderListener = GpsStatus.Listener {
            val gpsProviderEnabled = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)
            channel.trySend(gpsProviderEnabled)
        }

        // Send initial GPS provider status
        val gpsProviderEnabled = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)
        channel.trySend(gpsProviderEnabled)

        // Register the listener with the LocationManager
        // check Permission
        if (ActivityCompat.checkSelfPermission(
                context,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            locationManager.addGpsStatusListener(gpsProviderListener)
        } else {
            return null
        }


        return gpsProviderListener
    }*/
}