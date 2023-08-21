/*
 * *
 *  * Created by Bassam Abdulrazzaq on 8/21/23, 10:51 PM
 *  * Copyright (c) 2023 . All rights reserved.
 *  * Last modified 8/20/23, 2:14 AM
 *
 */

package com.bassamapps.weatherapp.core.designsystem.icon

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Stream
import androidx.compose.material.icons.filled.WaterDrop
import androidx.compose.material.icons.filled.WbSunny
import androidx.compose.material.icons.filled.WindPower
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material.icons.rounded.Close
import androidx.compose.material.icons.rounded.ErrorOutline
import androidx.compose.material.icons.rounded.Menu
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material.icons.rounded.Settings
import androidx.compose.material.icons.rounded.SignalWifiStatusbarConnectedNoInternet4
import androidx.compose.ui.graphics.vector.ImageVector

/**
 * Weather App icons. Material icons are [ImageVector]s, custom icons are drawable resource IDs.
 */
object WeIcons {
    val Menu = Icons.Rounded.Menu
    val Search = Icons.Rounded.Search

    val Error = Icons.Rounded.ErrorOutline
    val NoInternet = Icons.Rounded.SignalWifiStatusbarConnectedNoInternet4

    val UvIndex = Icons.Filled.WbSunny
    val Humidity = Icons.Filled.WaterDrop
    val Wind = Icons.Filled.WindPower
    val Sun = Icons.Filled.Stream

    val ArrowBack = Icons.Rounded.ArrowBack
    val Close = Icons.Rounded.Close

    val MoreVert = Icons.Default.MoreVert

    val Settings = Icons.Rounded.Settings
}
