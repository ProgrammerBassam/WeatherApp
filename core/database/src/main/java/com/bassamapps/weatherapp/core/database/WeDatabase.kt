/*
 * *
 *  * Created by Bassam Abdulrazzaq on 8/20/23, 2:14 AM
 *  * Copyright (c) 2023 . All rights reserved.
 *  * Last modified 8/18/23, 4:52 PM
 *
 */

package com.bassamapps.weatherapp.core.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.bassamapps.weatherapp.core.database.dao.RecentSearchQueryDao
import com.bassamapps.weatherapp.core.database.model.RecentSearchQueryEntity
import com.bassamapps.weatherapp.core.database.util.InstantConverter

@Database(
    entities = [
        RecentSearchQueryEntity::class,
    ],
    version = 1,
    autoMigrations = [
    ],
    exportSchema = true,
)
@TypeConverters(
    InstantConverter::class,
)
abstract class WeDatabase : RoomDatabase() {
    abstract fun recentSearchQueryDao(): RecentSearchQueryDao
}