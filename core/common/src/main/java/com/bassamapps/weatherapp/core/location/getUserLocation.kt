/*
 * *
 *  * Created by Bassam Abdulrazzaq on 8/21/23, 10:51 PM
 *  * Copyright (c) 2023 . All rights reserved.
 *  * Last modified 8/21/23, 2:07 AM
 *
 */

package com.bassamapps.weatherapp.core.location

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.location.LocationManager
import androidx.core.content.ContextCompat
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.Priority
import com.google.android.gms.tasks.CancellationTokenSource
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.tasks.await


/**
 * Is gps enabled
 *
 * @param context
 * @return
 */
fun isGpsEnabled(context: Context): Boolean {
    val locationManager = context.getSystemService(Context.LOCATION_SERVICE) as LocationManager
    return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)
}

/**
 * Is permission granted
 *
 * @param context
 * @return
 */
fun isPermissionGranted(context: Context): Boolean {
    val permission = Manifest.permission.ACCESS_COARSE_LOCATION
    val permission1 = Manifest.permission.ACCESS_FINE_LOCATION

    return (ContextCompat
        .checkSelfPermission(context, permission) == PackageManager.PERMISSION_GRANTED) && ContextCompat
        .checkSelfPermission(context, permission1) == PackageManager.PERMISSION_GRANTED && isGpsEnabled(context = context)
}

private lateinit var fusedLocationClient: FusedLocationProviderClient

/**
 * Get current location
 *
 * @param context
 * @return
 */
@ExperimentalCoroutinesApi
suspend fun getCurrentLocation(context: Context): String
    {
            fusedLocationClient = LocationServices.getFusedLocationProviderClient(context)

            var location = "Sana'a"

            if (ContextCompat.checkSelfPermission(
                    context,
                    Manifest.permission.ACCESS_COARSE_LOCATION
                ) == PackageManager.PERMISSION_GRANTED && ContextCompat.checkSelfPermission(
                    context,
                    Manifest.permission.ACCESS_FINE_LOCATION
                ) == PackageManager.PERMISSION_GRANTED
            ) {

                val currentLocation = fusedLocationClient
                    .getCurrentLocation(Priority.PRIORITY_BALANCED_POWER_ACCURACY,
                        CancellationTokenSource().token).await()

                if (currentLocation != null) {
                    location = "${currentLocation.latitude},${currentLocation.longitude}"
                }
            } else {
                location = "Sana'a"
            }


            return location
        }




