/*
 * *
 *  * Created by Bassam Abdulrazzaq on 8/21/23, 10:51 PM
 *  * Copyright (c) 2023 . All rights reserved.
 *  * Last modified 8/20/23, 2:14 AM
 *
 */

package com.bassamapps.weatherapp.core.network.model

import com.bassamapps.weatherapp.core.model.data.ConditionData
import kotlinx.serialization.Serializable

/**
 * Network condition
 *
 * @property text
 * @property icon
 * @property code
 * @constructor Create empty Network condition
 */
@Serializable
data class NetworkCondition (
    val text: String,
    val icon: String,
    val code: Double
)