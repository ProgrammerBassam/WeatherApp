/*
 * *
 *  * Created by Bassam Abdulrazzaq on 8/20/23, 2:14 AM
 *  * Copyright (c) 2023 . All rights reserved.
 *  * Last modified 8/17/23, 5:59 PM
 *
 */

package com.bassamapps.weatherapp.core.network.model

import com.bassamapps.weatherapp.core.model.data.ConditionData
import kotlinx.serialization.Serializable

/**
 * Network representation of [ConditionData]
 */
@Serializable
data class NetworkCondition (
    val text: String,
    val icon: String,
    val code: Double
)