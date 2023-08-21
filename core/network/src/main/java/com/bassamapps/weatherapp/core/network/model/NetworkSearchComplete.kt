/*
 * *
 *  * Created by Bassam Abdulrazzaq on 8/21/23, 10:51 PM
 *  * Copyright (c) 2023 . All rights reserved.
 *  * Last modified 8/20/23, 2:14 AM
 *
 */

package com.bassamapps.weatherapp.core.network.model

import kotlinx.serialization.Serializable

/**
 * Network search complete
 *
 * @property id
 * @property name
 * @property region
 * @property country
 * @property lat
 * @property lon
 * @property url
 * @constructor Create empty Network search complete
 */
@Serializable
data class NetworkSearchComplete (
    val id: Int,
    val name: String,
    val region: String,
    val country: String,
    val lat: Double,
    val lon: Double,
    val url: String
)