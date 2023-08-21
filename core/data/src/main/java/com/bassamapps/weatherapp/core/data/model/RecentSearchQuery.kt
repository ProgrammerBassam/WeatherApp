/*
 * *
 *  * Created by Bassam Abdulrazzaq on 8/21/23, 10:51 PM
 *  * Copyright (c) 2023 . All rights reserved.
 *  * Last modified 8/20/23, 2:14 AM
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