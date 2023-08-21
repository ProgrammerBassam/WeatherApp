/*
 * *
 *  * Created by Bassam Abdulrazzaq on 8/20/23, 2:14 AM
 *  * Copyright (c) 2023 . All rights reserved.
 *  * Last modified 8/18/23, 5:14 PM
 *
 */

package com.bassamapps.weatherapp.core.data.repository.fake

import com.bassamapps.weatherapp.core.data.model.RecentSearchQuery
import com.bassamapps.weatherapp.core.data.repository.impl.ImplRecentSearchRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import javax.inject.Inject

/**
 * Fake implementation of the [RecentSearchRepository]
 */
class FakeRecentSearchRepository @Inject constructor() : ImplRecentSearchRepository {
    override suspend fun insertOrReplaceRecentSearch(searchQuery: String) { /* no-op */ }

    override fun getRecentSearchQueries(limit: Int): Flow<List<RecentSearchQuery>> =
        flowOf(emptyList())

    override suspend fun clearRecentSearches() { /* no-op */ }
}