/*
 * *
 *  * Created by Bassam Abdulrazzaq on 8/20/23, 2:14 AM
 *  * Copyright (c) 2023 . All rights reserved.
 *  * Last modified 8/18/23, 4:30 PM
 *
 */

package com.bassamapps.weatherapp.core.database.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.datetime.Instant

/**
 * Defines an database entity that stored recent search queries.
 */
@Entity(
    tableName = "recentSearchQueries",
)
data class RecentSearchQueryEntity(
    @PrimaryKey
    val query: String,
    @ColumnInfo
    val queriedDate: Instant,
)