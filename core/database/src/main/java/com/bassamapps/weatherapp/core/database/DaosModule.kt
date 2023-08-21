/*
 * *
 *  * Created by Bassam Abdulrazzaq on 8/20/23, 2:14 AM
 *  * Copyright (c) 2023 . All rights reserved.
 *  * Last modified 8/18/23, 4:32 PM
 *
 */

package com.bassamapps.weatherapp.core.database

import com.bassamapps.weatherapp.core.database.dao.RecentSearchQueryDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object DaosModule {

    @Provides
    fun providesRecentSearchQueryDao(
        database: WeDatabase,
    ): RecentSearchQueryDao = database.recentSearchQueryDao()
}