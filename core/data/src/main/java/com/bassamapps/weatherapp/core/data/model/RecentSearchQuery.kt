/*
 * *
 *  * Created by Bassam Abdulrazzaq on 8/20/23, 2:14 AM
 *  * Copyright (c) 2023 . All rights reserved.
 *  * Last modified 8/18/23, 4:18 PM
 *
 */

package com.bassamapps.weatherapp.core.data.model

import com.bassamapps.weatherapp.core.database.model.RecentSearchQueryEntity
import kotlinx.datetime.Clock
import kotlinx.datetime.Instant

data class RecentSearchQuery(
    val query: String,
    val queriedDate: Instant = Clock.System.now(),
)

fun RecentSearchQueryEntity.asExternalModel() = RecentSearchQuery(
    query = query,
    queriedDate = queriedDate,
)