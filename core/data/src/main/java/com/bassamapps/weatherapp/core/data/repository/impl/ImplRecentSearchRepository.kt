/*
 * *
 *  * Created by Bassam Abdulrazzaq on 8/20/23, 2:14 AM
 *  * Copyright (c) 2023 . All rights reserved.
 *  * Last modified 8/18/23, 5:11 PM
 *
 */

package com.bassamapps.weatherapp.core.data.repository.impl

import com.bassamapps.weatherapp.core.data.model.RecentSearchQuery
import kotlinx.coroutines.flow.Flow

/**
 * Data layer interface for the recent searches.
 */
interface ImplRecentSearchRepository {
    /**
     * Get the recent search queries up to the number of queries specified as [limit].
     */
    fun getRecentSearchQueries(limit: Int): Flow<List<RecentSearchQuery>>

    /**
     * Insert or replace the [searchQuery] as part of the recent searches.
     */
    suspend fun insertOrReplaceRecentSearch(searchQuery: String)

    /**
     * Clear the recent searches.
     */
    suspend fun clearRecentSearches()
}