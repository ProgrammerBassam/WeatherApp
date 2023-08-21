/*
 * *
 *  * Created by Bassam Abdulrazzaq on 8/21/23, 10:51 PM
 *  * Copyright (c) 2023 . All rights reserved.
 *  * Last modified 8/20/23, 2:14 AM
 *
 */

package com.bassamapps.weatherapp.core.database.util

import androidx.room.TypeConverter
import kotlinx.datetime.Instant

/**
 * Instant converter
 *
 * @constructor Create empty Instant converter
 */
class InstantConverter {
    /**
     * Long to instant
     *
     * @param value
     * @return
     */
    @TypeConverter
    fun longToInstant(value: Long?): Instant? =
        value?.let(Instant::fromEpochMilliseconds)

    /**
     * Instant to long
     *
     * @param instant
     * @return
     */
    @TypeConverter
    fun instantToLong(instant: Instant?): Long? =
        instant?.toEpochMilliseconds()
}